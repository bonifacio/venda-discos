package br.com.beblue.vendadiscos.domain.service;

import java.util.List;

import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;

public interface GeneroServicePort {

	List<GeneroDTO> obterTodos();
}
