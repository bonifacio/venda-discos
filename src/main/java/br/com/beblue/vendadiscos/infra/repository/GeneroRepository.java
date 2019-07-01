package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

}
