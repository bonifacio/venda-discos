package br.com.beblue.vendadiscos.domain.model.dto;

import br.com.beblue.vendadiscos.domain.model.Genero;
import io.swagger.annotations.ApiModel;

@ApiModel("genero")
public class GeneroDTO {

	private Long codigo;
	
	private String nome;

	public GeneroDTO(Genero genero) {
		this.codigo = genero.getId();
		this.nome = genero.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}
}
