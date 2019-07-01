package br.com.beblue.vendadiscos.domain.model.dto;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.converter.ItemConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ApiModel("Venda")
@JsonIgnoreProperties(value = {"total", "totalCashback"}, allowGetters = true)
public class VendaDTO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;

    private List<ItemDTO> itens;

    public VendaDTO() {
    }

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

    public BigDecimal getTotal() {
        return itens.stream().map(ItemDTO::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double getTotalCashback() {
        return itens.stream().map(ItemDTO::getCashback).mapToDouble(BigDecimal::doubleValue).average().getAsDouble();
    }
}
