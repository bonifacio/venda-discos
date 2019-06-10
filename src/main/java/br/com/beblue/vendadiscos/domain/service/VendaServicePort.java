package br.com.beblue.vendadiscos.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;

public interface VendaServicePort {

	Page<VendaDTO> pesquisar(VendaFilter filtro, int numeroPagina, int tamanhoPagina);

	VendaDTO obterPorId(Long id);

	VendaDTO registrarVenda(List<ItemDTO> itens);
}
