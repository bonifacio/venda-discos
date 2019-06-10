package br.com.beblue.vendadiscos.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.service.DiscoServiceAdapter;
import br.com.beblue.vendadiscos.domain.service.GeneroServiceAdapter;
import br.com.beblue.vendadiscos.domain.service.SpotifyService;

@Component
public class ImportacaoSpotfy {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImportacaoSpotfy.class);
	
	private DiscoServiceAdapter discoService;

	private GeneroServiceAdapter generoService;

	private SpotifyService spotifyService;

	@Autowired
	public ImportacaoSpotfy(
			DiscoServiceAdapter discoService,
			GeneroServiceAdapter generoService,
			SpotifyService spotifyService) {
		this.discoService = discoService;
		this.generoService = generoService;
		this.spotifyService = spotifyService;
		
	}

	@PostConstruct
	public void init() {
		importarDiscos();
	}
	
	@Async("threadPoolTaskExecutor")
	public void importarDiscos() {
		LOG.info("Iniciando a importação dos discos");
		generoService.findAll().forEach(genero -> {
			LOG.info(genero.getNome());
			List<Disco> discos = spotifyService.obterDiscosPorGenero(genero.getNome());
			discos.forEach(disco -> {
				disco.setGenero(genero);
				disco.setPreco(new BigDecimal(Math.random() * 100));
				discoService.save(disco);
			});
		});
	}
}
