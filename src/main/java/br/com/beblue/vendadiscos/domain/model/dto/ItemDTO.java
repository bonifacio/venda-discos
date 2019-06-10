package br.com.beblue.vendadiscos.domain.model.dto;

import br.com.beblue.vendadiscos.domain.model.Item;

public class ItemDTO {
	
	private Long idDisco;
	private Integer quantidade;
	
	public ItemDTO() { }
	
	public ItemDTO(Item item) {
		idDisco = item.getDisco().getId();
		quantidade = item.getQuantidade();
	}
	
	public Long getIdDisco() {
		return idDisco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
}
