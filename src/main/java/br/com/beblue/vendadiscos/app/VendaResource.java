package br.com.beblue.vendadiscos.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.service.VendaServicePort;

@RestController
@RequestMapping("/venda")
public class VendaResource {

	private VendaServicePort vendaService;
	
	@Autowired
	public VendaResource(VendaServicePort vendaService) {
		this.vendaService = vendaService;
	}

	@GetMapping
	public Page<Venda> pesquisar(VendaFilter filtro) {
		return vendaService.pesquisar(filtro);
	}
}
