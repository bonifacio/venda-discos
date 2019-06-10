package br.com.beblue.vendadiscos.infra.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;

@Repository
public class GeneroRepositoryAdapter implements GeneroRepositoryPort {
	
	private GeneroRepository generoRepository;

	@Autowired
	public GeneroRepositoryAdapter(GeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}

	@Override
	public List<Genero> obterTodos() {
		return generoRepository.findAll();
	}
}
