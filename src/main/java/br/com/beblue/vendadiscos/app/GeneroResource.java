package br.com.beblue.vendadiscos.app;

import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;
import br.com.beblue.vendadiscos.domain.service.GeneroServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroResource {

    private final GeneroServicePort generoService;

    @Autowired
    public GeneroResource(GeneroServicePort generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public List<GeneroDTO> obterTodos() {
        return generoService.obterTodos();
    }
}
