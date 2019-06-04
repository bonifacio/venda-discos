package br.com.beblue.vendadiscos.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.beblue.vendadiscos.model.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	Page<Venda> findByDataBetweenOrderByDataDesc(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
}
