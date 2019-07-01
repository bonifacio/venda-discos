package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroServiceAdapter implements GeneroServicePort {

    private final GeneroRepositoryPort generoRepository;

    @Autowired
    public GeneroServiceAdapter(GeneroRepositoryPort generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<GeneroDTO> obterTodos() {
        List<Genero> generos = generoRepository.obterTodos();
        return generos.stream().map(GeneroDTO::new).collect(Collectors.toList());
    }

}
