package br.com.beblue.vendadiscos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Disco_;
import br.com.beblue.vendadiscos.domain.model.QDisco;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.infra.repository.DiscoRepository;

@Service
public class DiscoServiceAdapter implements DiscoServicePort {

	private DiscoRepository discoRepository;
	
	@Autowired
	public DiscoServiceAdapter(DiscoRepository discoRepository) {
		
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
