package br.com.beblue.vendadiscos.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.beblue.vendadiscos.domain.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
