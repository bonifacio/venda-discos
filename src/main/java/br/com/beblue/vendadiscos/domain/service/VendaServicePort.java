package br.com.beblue.vendadiscos.domain.service;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;

public interface VendaServicePort {

	Page<Venda> pesquisar(VendaFilter filtro);
}
