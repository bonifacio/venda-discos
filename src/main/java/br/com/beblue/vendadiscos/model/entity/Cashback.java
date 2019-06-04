package br.com.beblue.vendadiscos.model.entity;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cashback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek dia;
	
	private BigDecimal percentual;
}
