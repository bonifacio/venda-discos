package br.com.beblue.vendadiscos.domain.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;

public class VendaConverter {

	public static List<VendaDTO> paraDTO(List<Venda> vendas) {
		
		return vendas.stream().map(VendaDTO::new).collect(Collectors.toList());
	}

	public static VendaDTO paraDTO(Venda venda) {
		
		return new VendaDTO(venda);
	}
}
