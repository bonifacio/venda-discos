package br.com.beblue.vendadiscos.domain.repository;

import br.com.beblue.vendadiscos.domain.model.Artista;

public interface ArtistaRepositoryPort {

    Artista obterPorIdSpotify(String idSpotify);

    Artista salvar(Artista artista);

    void remover(Artista artista);
}
