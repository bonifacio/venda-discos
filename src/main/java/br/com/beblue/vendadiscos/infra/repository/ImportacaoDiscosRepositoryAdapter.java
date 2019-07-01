package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.repository.ImportacaoDiscosRepositoryPort;
import br.com.beblue.vendadiscos.infra.api.spotify.SpotifyApi;
import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistaSpotify;
import br.com.beblue.vendadiscos.infra.api.spotify.model.DiscoSpotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ImportacaoDiscosRepositoryAdapter implements ImportacaoDiscosRepositoryPort {

    private static final Logger LOG = LoggerFactory.getLogger(ImportacaoDiscosRepositoryAdapter.class);

    private final SpotifyApi spotifyApi;

    @Autowired
    public ImportacaoDiscosRepositoryAdapter(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    @Override
    public List<ArtistaSpotify> buscarArtistas(String genero, int offset, int limit) {

        try {
            return spotifyApi.obterArtistas(genero, offset, limit);
        } catch (Exception e) {
            LOG.error("Falha ao buscar os artistas: {}", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<DiscoSpotify> buscarDisco(String idArtista, int indiceDoDisco) {

        try {
            return Optional.of(spotifyApi.obterDisco(idArtista, indiceDoDisco));
        } catch (Exception e) {
            LOG.error("Falha ao buscar o disco: {}", e);
        }
        return Optional.empty();
    }
}
