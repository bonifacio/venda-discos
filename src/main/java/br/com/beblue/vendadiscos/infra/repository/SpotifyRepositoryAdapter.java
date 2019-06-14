package br.com.beblue.vendadiscos.infra.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.beblue.vendadiscos.domain.repository.SpotifyRepositoryPort;
import br.com.beblue.vendadiscos.infra.api.spotify.AlbumItem;
import br.com.beblue.vendadiscos.infra.api.spotify.SpotifyApi;
import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistsItem;

@Repository
public class SpotifyRepositoryAdapter implements SpotifyRepositoryPort {
	
	private SpotifyApi spotifyApi;

	@Autowired
	public SpotifyRepositoryAdapter(SpotifyApi spotifyApi) {
		this.spotifyApi = spotifyApi;
	}
	
	@Override
	public List<ArtistsItem> buscarArtistas(String genero, int offset, int limit) {
		
		return spotifyApi.obterArtistas(genero, offset, limit);
	}

	@Override
	public AlbumItem buscarDisco(String idArtista, int indiceDoDisco) {
		
		return spotifyApi.obterDisco(idArtista, indiceDoDisco);
	}
}
