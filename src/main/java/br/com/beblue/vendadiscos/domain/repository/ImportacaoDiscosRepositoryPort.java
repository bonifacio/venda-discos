package br.com.beblue.vendadiscos.domain.repository;

import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistaSpotify;
import br.com.beblue.vendadiscos.infra.api.spotify.model.DiscoSpotify;

import java.util.List;
import java.util.Optional;

public interface ImportacaoDiscosRepositoryPort {

    List<ArtistaSpotify> buscarArtistas(String genero, int offset, int limit);

    Optional<DiscoSpotify> buscarDisco(String idArtista, int indiceDoDisco);
}
