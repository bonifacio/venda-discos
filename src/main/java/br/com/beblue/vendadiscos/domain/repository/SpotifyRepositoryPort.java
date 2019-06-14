package br.com.beblue.vendadiscos.domain.repository;

import java.util.List;

import br.com.beblue.vendadiscos.infra.api.spotify.AlbumItem;
import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistsItem;

public interface SpotifyRepositoryPort {

	List<ArtistsItem> buscarArtistas(String genero, int offset, int limit);

	AlbumItem buscarDisco(String idArtista, int indiceDoDisco);
}
