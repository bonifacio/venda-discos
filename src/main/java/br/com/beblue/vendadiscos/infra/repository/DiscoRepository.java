package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface DiscoRepository extends JpaRepository<Disco, Long>, QuerydslPredicateExecutor<Disco> {

    Optional<Disco> findOneByIdSpotify(String idSpotify);
}
