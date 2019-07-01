package br.com.beblue.vendadiscos.infra.repository;

import br.com.beblue.vendadiscos.domain.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;

public interface VendaRepository extends JpaRepository<Venda, Long>, QuerydslPredicateExecutor<Venda> {

    Page<Venda> findByDataBetweenOrderByDataDesc(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
}
