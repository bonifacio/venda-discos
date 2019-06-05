package br.com.beblue.vendadiscos.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beblue.vendadiscos.model.entity.Venda;
import br.com.beblue.vendadiscos.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaResource {

	private VendaService vendaService;
	
	@Autowired
	public VendaResource(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@GetMapping
	public Page<Venda> pesquisar(VendaFilter filtro) {
		return vendaService.pesquisar(filtro);
	}
}
