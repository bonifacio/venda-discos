package br.com.beblue.vendadiscos.service;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.model.entity.Venda;
import br.com.beblue.vendadiscos.model.filter.VendaFilter;

public interface VendaService {

	Page<Venda> pesquisar(VendaFilter filtro);
}
