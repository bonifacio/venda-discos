package br.com.beblue.vendadiscos.domain.repository;

import java.util.List;

import br.com.beblue.vendadiscos.domain.model.Genero;

public interface GeneroRepositoryPort {

	List<Genero> obterTodos();
}
