package br.com.beblue.vendadiscos.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;
import br.com.beblue.vendadiscos.domain.model.dto.converter.GeneroConverter;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;

@Service
public class GeneroServiceAdapter implements GeneroServicePort {

	private GeneroRepositoryPort generoRepository;
	
	@Autowired
	public GeneroServiceAdapter(GeneroRepositoryPort generoRepository) {
		this.generoRepository = generoRepository;
	}

	@Override
	public List<GeneroDTO> obterTodos() {
		List<Genero> generos = generoRepository.obterTodos();
		return GeneroConverter.paraDTO(generos);
	}
}
