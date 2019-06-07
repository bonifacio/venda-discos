package br.com.beblue.vendadiscos.domain.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.com.beblue.vendadiscos.domain.model.Item;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;

public class ItemConverter {

	public static List<ItemDTO> paraDTO(final List<Item> itens) {
		return itens.stream().map(ItemDTO::new).collect(Collectors.toList());
	}

}
