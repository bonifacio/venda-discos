package br.com.beblue.vendadiscos.domain.repository;

import br.com.beblue.vendadiscos.domain.model.Genero;

import java.util.List;

public interface GeneroRepositoryPort {

    List<Genero> obterTodos();
}
