package br.com.beblue.vendadiscos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.infra.repository.VendaRepository;

@Service
public class VendaServiceAdapter implements VendaServicePort {
	
	private VendaRepository vendaRepository;
	
	@Autowired
	public VendaServiceAdapter(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	@Override
	public Page<Venda> pesquisar(VendaFilter filtro) {
		
		//PageRequest pageRequest = PageRequest.of(filtro.getPage(), filtro.getSize());
		//return vendaRepository.findByDataBetweenOrderByDataDesc(filtro.getDataInicio(), filtro.getDataFim(), pageRequest);
		return null;
	}
}
