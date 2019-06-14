package br.com.beblue.vendadiscos.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.repository.ArtistaRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.SpotifyRepositoryPort;
import br.com.beblue.vendadiscos.infra.api.spotify.AlbumItem;

@Service
public class SpotifyServiceAdapter implements SpotifyServicePort {

	private static final Logger LOG = LoggerFactory.getLogger(SpotifyServiceAdapter.class);
	
	private static final int SPOTIFY_TAMANHO_PAGINA = 10;
	
	@Value("${spotify.discos_por_genero}")
	private int discosPorGenero;

	private SpotifyRepositoryPort spotifyRepository;

	private ArtistaRepositoryPort artistaRepository;

	private GeneroRepositoryPort generoRepository;
	
	private DiscoRepositoryPort discoRepository;
	
	@Autowired
	public SpotifyServiceAdapter(
			SpotifyRepositoryPort spotifyRepository,
			ArtistaRepositoryPort artistaRepository,
			GeneroRepositoryPort generoRepository,
			DiscoRepositoryPort discoRepository) {
		
		this.spotifyRepository = spotifyRepository;
		this.artistaRepository = artistaRepository;
		this.generoRepository = generoRepository;
		this.discoRepository = discoRepository;
	}
	
	@Override
	@Async
	public void importarDiscos() {
		LOG.info("Iniciando a importação...");
		int quantidadeDePaginas = calcularQuantidadeDePaginas();
		for (int pagina = 0; pagina < quantidadeDePaginas; pagina++) {
			int offset = calcularOffset(pagina);
			int limit = calcularLimit(offset);
			importarDiscos(offset, limit);
		}
		LOG.info("Importação concluída...");
	}

	@Transactional
	private void importarDiscos(int offset, int limit) {
		
		List<Genero> generos = generoRepository.obterTodos();
		generos.parallelStream()
			.map(genero -> {
				return this.spotifyRepository.buscarArtistas(genero.getNome(), offset, limit).parallelStream().map(artistaSpotify -> {
					Artista artista = artistaRepository.obterPorIdSpotify(artistaSpotify.getId());
					if (artista == null) {
						artista = new Artista();
						artista.setNome(artistaSpotify.getName());
						artista.setIdSpotify(artistaSpotify.getId());
					} else {
						LOG.info("");
					}
					artista.adicionarGenero(genero);
					return artistaRepository.salvar(artista);
				}).collect(Collectors.toList());
			}).flatMap(List::stream).distinct().forEach(artista -> {
				LOG.info("Buscar disco");
				boolean continuarBuscandoDiscos = true;
				int indiceDoDisco = 0;
				while (continuarBuscandoDiscos) {
					AlbumItem discoSpotify = this.spotifyRepository.buscarDisco(artista.getIdSpotify(), indiceDoDisco);
					Disco disco = discoRepository.obterPorIdSpotify(discoSpotify.getId());
					if (disco == null) {
						continuarBuscandoDiscos = false;
						disco = new Disco();
						disco.setIdSpotify(discoSpotify.getId());
						disco.setNome(discoSpotify.getName());
						disco.setPreco(new BigDecimal(Math.random() * 100));
					} else {
						if (artista.getGeneros().size() > 1) {
							continuarBuscandoDiscos = false;
						}
					}
					disco.adicionarArtista(artista);
					discoRepository.salvar(disco);
					indiceDoDisco++;
				}
			});
			//collect(Collectors.toList());
		
		
		//importar(genero, offset, limit);
		
//		ArtistsItem artistasSpotify = this.spotifyRepository.buscarArtistas(offset, limit);
//		List<Artista> artistas = 
//		this.spotifyRepository.importarDiscos(offset, limit);
	}

	private int calcularLimit(int offset) {
		
		int quantidadeRestante = discosPorGenero - offset + 1;
		if (quantidadeRestante > SPOTIFY_TAMANHO_PAGINA) {
			return SPOTIFY_TAMANHO_PAGINA;
		}
		return quantidadeRestante;
	}

	private int calcularOffset(int pagina) {
		int offset = pagina * SPOTIFY_TAMANHO_PAGINA;
		if (offset > 0) {
			offset++;
		}
		return offset;
	}
	
	private int calcularQuantidadeDePaginas() {
		int quantidadeDePaginas = discosPorGenero / SPOTIFY_TAMANHO_PAGINA;
		int quantidadeDeItensParaProximaPagina = discosPorGenero % SPOTIFY_TAMANHO_PAGINA;
		if (quantidadeDeItensParaProximaPagina > 0) {
			quantidadeDePaginas++;
		}
		return quantidadeDePaginas;
	}
	
//	private static final Logger LOG = LoggerFactory.getLogger(SpotifyServiceAdapter.class);
//	
//	private DiscoServiceAdapter discoService;
//
//	private GeneroServiceAdapter generoService;
//
//	private SpotifyService spotifyService;
//
//	@Autowired
//	public SpotifyServiceAdapter(
//			DiscoServiceAdapter discoService,
//			GeneroServiceAdapter generoService,
//			SpotifyService spotifyService) {
//		this.discoService = discoService;
//		this.generoService = generoService;
//		this.spotifyService = spotifyService;
//		
//	}
//
//	@PostConstruct
//	public void init() {
//		importarDiscos();
//	}
//	
//	@Async("threadPoolTaskExecutor")
//	public void importarDiscos() {
//		LOG.info("Iniciando a importação dos discos");
//		generoService.findAll().forEach(genero -> {
//			LOG.info(genero.getNome());
//			List<Disco> discos = spotifyService.obterDiscosPorGenero(genero.getNome());
//			discos.forEach(disco -> {
//				disco.setGenero(genero);
//				disco.setPreco(new BigDecimal(Math.random() * 100));
//				discoService.save(disco);
//			});
//		});
//	}
}
