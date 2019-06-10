package br.com.beblue.vendadiscos.infra.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.QDisco;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;

@Repository
public class DiscoRepositoryAdapter implements DiscoRepositoryPort {
	
	private DiscoRepository discoRepository;

	@Autowired
	public DiscoRepositoryAdapter(DiscoRepository discoRepository) {
		this.discoRepository = discoRepository;
	}

	@Override
	public Page<Disco> pesquisar(DiscoFilter filtro, Pagina pagina, Ordenacao ordenacao) {
		
		Predicate booleanBuilder = obterPredicatePesquisar(filtro);
		PageRequest pageRequest = obterPageRequestPesquisar(pagina, ordenacao);
		return discoRepository.findAll(booleanBuilder, pageRequest);
	}

	private PageRequest obterPageRequestPesquisar(Pagina pagina, Ordenacao ordenacao) {
		
		Direction direction = Direction.valueOf(ordenacao.getDirecao().name());
		Sort sort = Sort.by(direction, ordenacao.getCampo());
		return PageRequest.of(pagina.getNumero(), pagina.getTamanho(), sort);
	}

	private Predicate obterPredicatePesquisar(DiscoFilter filtro) {
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (filtro.getIdGenero() != null) {
			booleanBuilder.and(QDisco.disco.genero.id.eq(filtro.getIdGenero()));
		}
		return booleanBuilder;
	}

	@Override
	public Optional<Disco> obterPorId(Long id) {
		
		return discoRepository.findById(id);
	}
}
