package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.repository.ArtistaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistaRepositoryAdapter implements ArtistaRepositoryPort {

    private ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaRepositoryAdapter(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Override
    public Artista obterPorIdSpotify(String idSpotify) {
        return artistaRepository.findOneByIdSpotify(idSpotify);
    }

    @Override
    public Artista salvar(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public void remover(Artista artista) {
        artistaRepository.delete(artista);
    }
}
