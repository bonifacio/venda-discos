package br.com.beblue.vendadiscos.domain.model.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.converter.ItemConverter;
import io.swagger.annotations.ApiModel;

@ApiModel("Venda")
public class VendaDTO {

	private Long id;
	private LocalDateTime data;
	private List<ItemDTO> itens;

	public VendaDTO(Venda venda) {
		id = venda.getId();
		data = venda.getData();
		itens = ItemConverter.paraDTO(venda.getItens());
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public List<ItemDTO> getItens() {
		return Collections.unmodifiableList(itens);
	}
}
