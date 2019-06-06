package br.com.beblue.vendadiscos.domain.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;

public class DiscoConverter {

	public static List<DiscoDTO> paraDTO(List<Disco> discos) {
		
		return discos.stream().map(d -> new DiscoDTO(d)).collect(Collectors.toList());
	}

	public static DiscoDTO paraDTO(Disco disco) {
		
		return new DiscoDTO(disco);
	}

}
