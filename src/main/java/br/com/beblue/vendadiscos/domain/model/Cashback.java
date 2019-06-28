package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Cashback extends EntityBase {
	
	@ManyToOne
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek dia;
	
	private BigDecimal percentual;

	public Cashback() {
		
	}
	
	public Cashback(DayOfWeek dia, BigDecimal percentual) {
		this.dia = dia;
		this.percentual = percentual;
	}
	
	public DayOfWeek getDia() {
		return dia;
	}
	
	public BigDecimal getPercentual() {
		return percentual;
	}
}
