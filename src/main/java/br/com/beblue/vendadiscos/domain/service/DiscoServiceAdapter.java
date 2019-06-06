package br.com.beblue.vendadiscos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Disco_;
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
	public Page<Disco> pesquisar(DiscoFilter filtro, int numeroPagina, int tamanhoPagina) {

		Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
		Ordenacao ordenacao = new Ordenacao(Disco_.nome.getName(), Ordenacao.Direcao.ASC);
		return discoRepository.pesquisar(filtro, pagina, ordenacao);
	}

	@Override
	public Disco obterPorId(Long id) {
		
		return discoRepository.obterPorId(id).orElse(null);
	}
}
