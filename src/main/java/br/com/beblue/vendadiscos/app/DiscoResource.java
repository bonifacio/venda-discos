package br.com.beblue.vendadiscos.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.domain.service.DiscoServicePort;

@RestController
@RequestMapping("/disco")
public class DiscoResource {

	private DiscoServicePort discoService;
	
	@Autowired
	public DiscoResource(DiscoServicePort discoService) {
		this.discoService = discoService;
	}

	@GetMapping
	public Page<DiscoDTO> pesquisar(
			@RequestParam(defaultValue = "0") int numeroPagina,
			@RequestParam(defaultValue = "10") int tamanhoPagina,
			DiscoFilter filtro) {
		
		return discoService.pesquisar(filtro, numeroPagina, tamanhoPagina);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DiscoDTO> obterPorId(@PathVariable Long id) {
		
		DiscoDTO disco = discoService.obterPorId(id);
		if (disco == null) {		
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(disco);
	}
}
