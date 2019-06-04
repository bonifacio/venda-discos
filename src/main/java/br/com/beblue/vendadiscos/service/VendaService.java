package br.com.beblue.vendadiscos.service;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.model.FiltroVenda;
import br.com.beblue.vendadiscos.model.entity.Venda;

public interface VendaService {

	Page<Venda> pesquisar(FiltroVenda filtro);
}
