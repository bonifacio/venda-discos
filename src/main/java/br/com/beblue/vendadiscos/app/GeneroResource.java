package br.com.beblue.vendadiscos.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;
import br.com.beblue.vendadiscos.domain.service.GeneroServicePort;

@RestController
@RequestMapping("/genero")
public class GeneroResource {

	private GeneroServicePort generoService;
	
	@Autowired
	public GeneroResource(GeneroServicePort generoService) {
		this.generoService = generoService;
	}

	@GetMapping
	public List<GeneroDTO> obterTodos() {
		return generoService.obterTodos();
	}
}
