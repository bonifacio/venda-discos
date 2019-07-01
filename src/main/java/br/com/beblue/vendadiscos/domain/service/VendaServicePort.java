package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendaServicePort {

    Page<VendaDTO> pesquisar(VendaFilter filtro, int numeroPagina, int tamanhoPagina);

    VendaDTO obterPorId(Long id);

    VendaDTO registrarVenda(List<ItemDTO> itens);
}
