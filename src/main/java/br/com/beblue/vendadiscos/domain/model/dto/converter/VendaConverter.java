package br.com.beblue.vendadiscos.domain.model.dto.converter;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;

import java.util.List;
import java.util.stream.Collectors;

public class VendaConverter {

    private VendaConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<VendaDTO> paraDTO(List<Venda> vendas) {

        return vendas.stream().map(VendaDTO::new).collect(Collectors.toList());
    }

    public static VendaDTO paraDTO(Venda venda) {

        return new VendaDTO(venda);
    }
}
