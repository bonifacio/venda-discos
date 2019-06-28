package br.com.beblue.vendadiscos.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Disco_;
import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;
import br.com.beblue.vendadiscos.domain.model.dto.converter.DiscoConverter;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;

@Service
public class DiscoServiceAdapter implements DiscoServicePort {

	private DiscoRepositoryPort discoRepository;
	
	@Autowired
	public DiscoServiceAdapter(DiscoRepositoryPort discoRepository) {
		
		this.discoRepository = discoRepository;
	}

	@Override
	public Page<DiscoDTO> pesquisar(DiscoFilter filtro, int numeroPagina, int tamanhoPagina) {

		Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
		Ordenacao ordenacao = new Ordenacao(Disco_.NOME, Ordenacao.Direcao.ASC);
		Page<Disco> discosPaginados = discoRepository.pesquisar(filtro, pagina, ordenacao);
		return new PageImpl<>(
				DiscoConverter.paraDTO(discosPaginados.getContent()),
				discosPaginados.getPageable(),
				discosPaginados.getTotalElements());
	}

	@Override
	public DiscoDTO obterPorId(Long id) {
		
		Optional<Disco> disco = discoRepository.obterPorId(id);
		if (!disco.isPresent()) {
			return null;
		}
		return DiscoConverter.paraDTO(disco.get());
	}
}
