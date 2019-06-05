package br.com.beblue.vendadiscos.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.model.entity.Disco;
import br.com.beblue.vendadiscos.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.service.DiscoService;

@RestController
@RequestMapping("/disco")
public class DiscoResource {

	private DiscoService discoService;
	
	@Autowired
	public DiscoResource(DiscoService discoService) {
		this.discoService = discoService;
	}

	@GetMapping
	public Page<Disco> pesquisar(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			DiscoFilter discoFilter) {
		
		return discoService.pesquisar(page, size, discoFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Disco> obterPorId(@PathVariable Long id) {
		
		Disco disco = discoService.obterPorId(id);
		if (disco == null) {		
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(disco);
	}
}
