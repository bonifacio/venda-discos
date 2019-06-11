package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Disco extends EntityBase {

	private String idSpotify;
	
	@Size(max = 150)
	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal preco;
	
	@ManyToMany
	private List<Artista> artistas;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

//	public BigDecimal getCashback() {
//		
//		return this.preco.multiply(this.genero.getPercentualCashback());
//	}
}
