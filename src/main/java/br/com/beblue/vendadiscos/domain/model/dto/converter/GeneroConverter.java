package br.com.beblue.vendadiscos.domain.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;

public class GeneroConverter {

	public static List<GeneroDTO> paraDTO(List<Genero> generos) {
		
		return generos.stream().map(g -> new GeneroDTO(g)).collect(Collectors.toList());
	}
}
