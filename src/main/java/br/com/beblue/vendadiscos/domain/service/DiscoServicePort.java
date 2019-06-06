package br.com.beblue.vendadiscos.domain.service;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;

public interface DiscoServicePort {

	Page<Disco> pesquisar(DiscoFilter discoFilter, int numeroPagina, int tamanhoPagina);

	Disco obterPorId(Long id);
}
