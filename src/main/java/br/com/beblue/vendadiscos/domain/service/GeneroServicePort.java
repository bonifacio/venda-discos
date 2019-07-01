package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;

import java.util.List;

public interface GeneroServicePort {

    List<GeneroDTO> obterTodos();
}
