package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Disco extends EntityBase {

	@Size(max = 100)
	@NotBlank
	private String nome;
	
	@Size(max = 300)
	private String artistas;
	
	@NotNull
	private BigDecimal preco;
	
	@ManyToOne
	private Genero genero;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArtistas() {
		return artistas;
	}

	public void setArtistas(String artistas) {
		this.artistas = artistas;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public BigDecimal getCashback() {
		
		return this.preco.multiply(this.genero.getPercentualCashback());
	}
}
