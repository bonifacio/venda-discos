package br.com.beblue.vendadiscos.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class FiltroVenda extends Pagination {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataFim;

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
}
