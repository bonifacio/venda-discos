package br.com.beblue.vendadiscos.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.model.entity.Genero;
import br.com.beblue.vendadiscos.service.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroResource {

	private GeneroService generoService;
	
	@Autowired
	public GeneroResource(GeneroService generoService) {
		this.generoService = generoService;
	}

	@GetMapping
	public List<Genero> obterTodos() {
		return generoService.obterTodos();
	}
}
