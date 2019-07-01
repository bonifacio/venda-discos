package br.com.beblue.vendadiscos.domain.model.dto.converter;

import br.com.beblue.vendadiscos.domain.model.Item;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ItemConverter {

    private ItemConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ItemDTO> paraDTO(final List<Item> itens) {
        return itens.stream().map(ItemDTO::new).collect(Collectors.toList());
    }

}
