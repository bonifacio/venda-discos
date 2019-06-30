package br.com.beblue.vendadiscos.domain.model.dto;

import br.com.beblue.vendadiscos.domain.model.Genero;
import io.swagger.annotations.ApiModel;

@ApiModel("Genero")
public class GeneroDTO {

	private Long id;
	
	private String nome;
	
	public GeneroDTO() { }

	public GeneroDTO(Genero genero) {
		this.id = genero.getId();
		this.nome = genero.getNome();
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
