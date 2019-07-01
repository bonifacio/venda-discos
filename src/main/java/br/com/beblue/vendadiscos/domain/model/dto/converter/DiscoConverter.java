package br.com.beblue.vendadiscos.domain.model.dto.converter;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoConverter {

    private DiscoConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<DiscoDTO> paraDTO(List<Disco> discos) {

        return discos.stream().map(DiscoDTO::new).collect(Collectors.toList());
    }

    public static DiscoDTO paraDTO(Disco disco) {

        return new DiscoDTO(disco);
    }

}
