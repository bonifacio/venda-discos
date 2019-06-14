package br.com.beblue.vendadiscos.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.beblue.vendadiscos.domain.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	Artista findOneByIdSpotify(String idSpotify);
}
