package br.com.beblue.vendadiscos.service;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.model.entity.Disco;
import br.com.beblue.vendadiscos.model.filter.DiscoFilter;

public interface DiscoService {

	Page<Disco> pesquisar(int page, int size, DiscoFilter discoFilter);

	Disco obterPorId(Long id);
}
