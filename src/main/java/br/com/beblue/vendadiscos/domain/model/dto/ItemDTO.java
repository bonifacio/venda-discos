package br.com.beblue.vendadiscos.domain.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.beblue.vendadiscos.domain.model.Item;
import io.swagger.annotations.ApiModel;

@ApiModel("Item")
public class ItemDTO {
	
	@NotNull
	private Long idDisco;
	
	@NotNull
	@Min(1)
	private Integer quantidade;

	private BigDecimal cashback;
	
	public ItemDTO(Item item) {
		idDisco = item.getDisco().getId();
		quantidade = item.getQuantidade();
		cashback = item.getCashback();
	}
	
	public Long getIdDisco() {
		return idDisco;
	}
	
	public void setIdDisco(Long idDisco) {
		this.idDisco = idDisco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getCashback() {
		return cashback;
	}
}
