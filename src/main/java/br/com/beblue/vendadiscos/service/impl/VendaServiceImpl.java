package br.com.beblue.vendadiscos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.model.entity.Venda;
import br.com.beblue.vendadiscos.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.repository.VendaRepository;
import br.com.beblue.vendadiscos.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {
	
	private VendaRepository vendaRepository;
	
	@Autowired
	public VendaServiceImpl(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}

	@Override
	public Page<Venda> pesquisar(VendaFilter filtro) {
		
		//PageRequest pageRequest = PageRequest.of(filtro.getPage(), filtro.getSize());
		//return vendaRepository.findByDataBetweenOrderByDataDesc(filtro.getDataInicio(), filtro.getDataFim(), pageRequest);
		return null;
	}
}
