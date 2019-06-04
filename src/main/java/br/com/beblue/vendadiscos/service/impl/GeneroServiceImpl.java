package br.com.beblue.vendadiscos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.model.entity.Genero;
import br.com.beblue.vendadiscos.repository.GeneroRepository;
import br.com.beblue.vendadiscos.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

	private GeneroRepository generoRepository;
	
	@Autowired
	public GeneroServiceImpl(GeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}

	@Override
	public List<Genero> obterTodos() {
		return generoRepository.findAll();
	}

}
