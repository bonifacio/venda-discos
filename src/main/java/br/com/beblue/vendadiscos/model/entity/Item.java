package br.com.beblue.vendadiscos.model.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	private Venda venda;
	
	@OneToOne
	@NotNull
	private Disco disco;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private BigDecimal cashback;
}
