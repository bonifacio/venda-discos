package br.com.beblue.vendadiscos.domain.service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.repository.ArtistaRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.ImportacaoDiscosRepositoryPort;
import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistaSpotify;
import br.com.beblue.vendadiscos.infra.api.spotify.model.DiscoSpotify;

@Service
public class ImportacaoDiscosServiceAdapter implements ImportacaoDiscosServicePort {

	private static final Logger LOG = LoggerFactory.getLogger(ImportacaoDiscosServiceAdapter.class);

	@Value("${importacao-discos.discos_por_genero}")
	private int discosPorGenero;

	@Value("${importacao-discos.discos_por_vez}")
	private int discosPorVez;

	private ImportacaoDiscosRepositoryPort importacaoDiscosRepository;

	private ArtistaRepositoryPort artistaRepository;

	private GeneroRepositoryPort generoRepository;

	private DiscoRepositoryPort discoRepository;

	@Autowired
	public ImportacaoDiscosServiceAdapter(
			ImportacaoDiscosRepositoryPort importacaoDiscosRepository,
			ArtistaRepositoryPort artistaRepository,
			GeneroRepositoryPort generoRepository,
			DiscoRepositoryPort discoRepository) {

		this.importacaoDiscosRepository = importacaoDiscosRepository;
		this.artistaRepository = artistaRepository;
		this.generoRepository = generoRepository;
		this.discoRepository = discoRepository;
	}

	@Override
	@Async
	public void importar() {

		LOG.info("Iniciando a importação dos discos...");
		int quantidadeDeBuscas = calcularQuantidadeDeBuscas();
		for (int pagina = 0; pagina < quantidadeDeBuscas; pagina++) {
			int offset = calcularOffset(pagina);
			int limit = calcularLimit(offset);
			importarDiscosPorPagina(offset, limit);
		}
		LOG.info("Importação dos discos concluída...");
	}

	@Transactional
	private void importarDiscosPorPagina(int offset, int limit) {
		List<Genero> generos = generoRepository.obterTodos();
		Stream<Artista> artistas = importarArtistasPorPagina(offset, limit, generos);
		importarDiscos(artistas);
	}

	private void importarDiscos(Stream<Artista> artistas) {

		artistas.parallel().forEach(artista -> {
			boolean continuarBuscandoDiscos = true;
			int indiceDoDisco = 0;
			while (continuarBuscandoDiscos) {
				Optional<DiscoSpotify> discoImportado = this.importacaoDiscosRepository.buscarDisco(artista.getIdSpotify(), indiceDoDisco);
				if (discoImportado.isPresent()) {
					Disco disco = salvarDiscoImportado(artista, discoImportado.get());
					continuarBuscandoDiscos = verificarSeDeveContinuarBuscandoDiscos(disco);
					indiceDoDisco++;
				} else {
					continuarBuscandoDiscos = false;
					excluirArtistaSemDisco(artista);
				}
			}
		});
	}

	private void excluirArtistaSemDisco(Artista artista) {
		List<Disco> discos = discoRepository.obterDiscos(artista);
		if (CollectionUtils.isEmpty(discos)) {
			artistaRepository.remover(artista);
		}
	}

	private boolean verificarSeDeveContinuarBuscandoDiscos(Disco disco) {

		if (disco.possuiApenasUmArtista()) {
			return false;
		}
		if (disco.possuiMaisDeUmGenero()) {
			return false;
		}
		return true;
	}

	private Disco salvarDiscoImportado(Artista artista, DiscoSpotify discoImportado) {

		LOG.info("Salvando disco {} [{}]", discoImportado.getName(), discoImportado.getId());
		Optional<Disco> discoSalvo = discoRepository.obterPorIdSpotify(discoImportado.getId());
		Disco disco = new Disco();
		if (discoSalvo.isPresent()) {
			disco = discoSalvo.get();
		} else {
			disco.setIdSpotify(discoImportado.getId());
			disco.setNome(discoImportado.getName());
			disco.setPreco(BigDecimal.valueOf(new SecureRandom().nextInt(100)));
		}
		disco.adicionarArtista(artista);
		return discoRepository.salvar(disco);
	}

	private Stream<Artista> importarArtistasPorPagina(int offset, int limit, List<Genero> generos) {

		return generos.stream()
				.map(genero -> {
					List<ArtistaSpotify> artistasImportados = this.importacaoDiscosRepository.buscarArtistas(genero.getNome(), offset, limit);
					return artistasImportados.parallelStream().map(artistaSpotify -> salvarArtistaImportado(genero, artistaSpotify)).collect(Collectors.toList());
				})
				.flatMap(List::stream)
				.distinct();
	}

	private Artista salvarArtistaImportado(Genero genero, ArtistaSpotify artistaSpotify) {

		LOG.info("Salvando artista {} [{}]", artistaSpotify.getName(), artistaSpotify.getId());
		Artista artista = artistaRepository.obterPorIdSpotify(artistaSpotify.getId());
		if (artista == null) {
			artista = new Artista();
			artista.setNome(artistaSpotify.getName());
			artista.setIdSpotify(artistaSpotify.getId());
		} 
		artista.adicionarGenero(genero);
		return artistaRepository.salvar(artista);
	}

	private int calcularLimit(int offset) {

		int quantidadeRestante = discosPorGenero - offset + 1;
		if (quantidadeRestante > discosPorVez) {
			return discosPorVez;
		}
		return quantidadeRestante;
	}

	private int calcularOffset(int pagina) {
		if (pagina == 0) {
			return 0;
		}
		return pagina * discosPorVez;
	}

	private int calcularQuantidadeDeBuscas() {

		int quantidadeDePaginas = discosPorGenero / discosPorVez;
		int quantidadeDeItensParaProximaPagina = discosPorGenero % discosPorVez;
		if (quantidadeDeItensParaProximaPagina > 0) {
			quantidadeDePaginas++;
		}
		return quantidadeDePaginas;
	}
}
