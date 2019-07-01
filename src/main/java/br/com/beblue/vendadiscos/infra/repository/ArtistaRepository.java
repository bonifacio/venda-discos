package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Artista findOneByIdSpotify(String idSpotify);
}
