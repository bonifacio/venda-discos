package br.com.beblue.vendadiscos.infra.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.repository.SpotifyRepositoryPort;
import br.com.beblue.vendadiscos.infra.api.spotify.SpotifyApi;

@Repository
public class SpotifyRepositoryAdapter implements SpotifyRepositoryPort {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpotifyRepositoryPort.class);
	
	private GeneroRepository generoRepository;

	private SpotifyApi spotifyApi;

	private DiscoRepository discoRepository;

	@Autowired
	public SpotifyRepositoryAdapter(
			GeneroRepository generoRepository,
			DiscoRepository discoRepository,
			SpotifyApi spotifyApi) {
		this.generoRepository = generoRepository;
		this.discoRepository = discoRepository;
		this.spotifyApi = spotifyApi;
	}

	@Override
	public void importarDiscos(int offset, int limit) {
		List<Genero> generos = generoRepository.findAll();
		generos.parallelStream().forEach(genero -> {
			LOG.info("Importando {} discos do gênero {}", limit, genero.getNome());
			importar(genero, offset, limit);
		});
	}

	@Transactional
	private void importar(Genero genero, int offset, int limit) {
		List<Disco> discos = this.spotifyApi.obterDiscosPorGenero(genero, offset, limit);
		discoRepository.saveAll(discos);
	}
}
