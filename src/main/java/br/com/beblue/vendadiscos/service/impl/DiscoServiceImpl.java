package br.com.beblue.vendadiscos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import br.com.beblue.vendadiscos.model.entity.Disco;
import br.com.beblue.vendadiscos.model.entity.Disco_;
import br.com.beblue.vendadiscos.model.entity.QDisco;
import br.com.beblue.vendadiscos.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.repository.DiscoRepository;
import br.com.beblue.vendadiscos.service.DiscoService;

@Service
public class DiscoServiceImpl implements DiscoService {

	private DiscoRepository discoRepository;
	
	@Autowired
	public DiscoServiceImpl(DiscoRepository discoRepository) {
		
		this.discoRepository = discoRepository;
	}

	@Override
	public Page<Disco> pesquisar(int page, int size, DiscoFilter discoFilter) {

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (discoFilter.getIdGenero() != null) {
			booleanBuilder.and(QDisco.disco.genero.id.eq(discoFilter.getIdGenero()));
		}
		return discoRepository.findAll(booleanBuilder, PageRequest.of(page, size, Sort.by(Disco_.nome.getName())));
	}

	@Override
	public Disco obterPorId(Long id) {
		
		return discoRepository.findById(id).orElse(null);
	}
}
