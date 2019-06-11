package br.com.beblue.vendadiscos.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.repository.SpotifyRepositoryPort;

@Service
public class SpotifyServiceAdapter implements SpotifyServicePort {

	private static final Logger LOG = LoggerFactory.getLogger(SpotifyServiceAdapter.class);
	
	private static final int SPOTIFY_TAMANHO_PAGINA = 10;
	
	@Value("${spotify.discos_por_genero}")
	private int discosPorGenero;

	private SpotifyRepositoryPort spotifyRepository;
	
	public SpotifyServiceAdapter(SpotifyRepositoryPort spotifyRepository) {
		this.spotifyRepository = spotifyRepository;
	}
	
	@Override
	@Async
	public void importarDiscos() {
		LOG.info("Iniciando a importação...");
		int quantidadeDePaginas = calcularQuantidadeDePaginas();
		for (int pagina = 0; pagina < quantidadeDePaginas; pagina++) {
			LOG.info("Buscando a {}ª página", pagina + 1);
			int offset = calcularOffset(pagina);
			int limit = calcularLimit(offset);
			LOG.info("offset: {} / limit: {}", offset, limit);
			long timeMillis = System.currentTimeMillis();
			this.spotifyRepository.importarDiscos(offset, limit);
			LOG.info("Finalizando a página {} em {} ms", pagina + 1, System.currentTimeMillis() - timeMillis);
		}
		LOG.info("Importação concluída...");
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
