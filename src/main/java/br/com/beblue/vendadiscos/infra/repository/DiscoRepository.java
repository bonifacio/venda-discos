package br.com.beblue.vendadiscos.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.beblue.vendadiscos.domain.model.Disco;

public interface DiscoRepository extends JpaRepository<Disco, Long>, QuerydslPredicateExecutor<Disco>{
	
}