package br.com.beblue.vendadiscos.domain.model.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Genero;
import io.swagger.annotations.ApiModel;

@ApiModel("Disco")
public class DiscoDTO {

	private Long id;
	private String nome;
	private Set<String> artistas;
	private Set<String> genero;
	private BigDecimal preco;

	public DiscoDTO(Disco disco) {
		id = disco.getId();
		nome = disco.getNome();
		artistas = disco.getArtistas().stream().map(Artista::getNome).collect(Collectors.toSet());
		genero = disco.getArtistas().stream().map(Artista::getGeneros).flatMap(Set::stream).map(Genero::getNome).collect(Collectors.toSet());
		preco = disco.getPreco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<String> getArtistas() {
		return artistas;
	}

	public Set<String> getGenero() {
		return genero;
	}

	public BigDecimal getPreco() {
		return preco;
	}
}
