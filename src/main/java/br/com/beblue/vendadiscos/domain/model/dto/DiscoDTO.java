package br.com.beblue.vendadiscos.domain.model.dto;

import java.math.BigDecimal;

import br.com.beblue.vendadiscos.domain.model.Disco;
import io.swagger.annotations.ApiModel;

@ApiModel("Disco")
public class DiscoDTO {

	private Long id;
	private String nome;
	private String artistas;
	private String genero;
	private BigDecimal preco;

	public DiscoDTO(Disco disco) {
		id = disco.getId();
		nome = disco.getNome();
		artistas = disco.getArtistas();
		genero = disco.getGenero().getNome();
		preco = disco.getPreco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getArtistas() {
		return artistas;
	}

	public String getGenero() {
		return genero;
	}

	public BigDecimal getPreco() {
		return preco;
	}
}
