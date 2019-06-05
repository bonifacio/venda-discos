package br.com.beblue.vendadiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.beblue.vendadiscos.model.entity.Disco;

public interface DiscoRepository extends JpaRepository<Disco, Long>, QuerydslPredicateExecutor<Disco>{
	
}
