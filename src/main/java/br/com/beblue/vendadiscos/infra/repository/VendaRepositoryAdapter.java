package br.com.beblue.vendadiscos.infra.repository;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.beblue.vendadiscos.domain.model.QVenda;
import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.VendaRepositoryPort;

@Repository
public class VendaRepositoryAdapter implements VendaRepositoryPort {

	private VendaRepository vendaRepository;

	public VendaRepositoryAdapter(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}
	
	@Override
	public Page<Venda> pesquisar(VendaFilter filtro, Pagina pagina, Ordenacao ordenacao) {
		
		Predicate booleanBuilder = obterPredicatePesquisar(filtro);
		PageRequest pageRequest = obterPageRequestPesquisar(pagina, ordenacao);
		return vendaRepository.findAll(booleanBuilder, pageRequest);
	}

	private PageRequest obterPageRequestPesquisar(Pagina pagina, Ordenacao ordenacao) {
		
		Direction direction = Direction.valueOf(ordenacao.getDirecao().name());
		Sort sort = Sort.by(direction, ordenacao.getCampo());
		return PageRequest.of(pagina.getNumero(), pagina.getTamanho(), sort);
	}

	private Predicate obterPredicatePesquisar(VendaFilter filtro) {
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		if (filtro.getDataInicio() != null) {
			booleanBuilder.and(QVenda.venda.data.goe(filtro.getDataInicio().atTime(LocalTime.MIN)));
		}
		if (filtro.getDataFim() != null) {
			booleanBuilder.and(QVenda.venda.data.loe(filtro.getDataFim().atTime(LocalTime.MAX)));
		}
		return booleanBuilder;
	}

	@Override
	public Optional<Venda> obterPorId(Long id) {
		
		return vendaRepository.findById(id);
	}

}
