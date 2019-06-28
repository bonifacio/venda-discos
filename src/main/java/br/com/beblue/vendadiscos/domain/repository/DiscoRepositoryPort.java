package br.com.beblue.vendadiscos.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;

public interface DiscoRepositoryPort {

	Page<Disco> pesquisar(DiscoFilter discoFilter, Pagina pagina, Ordenacao ordenacao);

	Optional<Disco> obterPorId(Long id);

	Optional<Disco> obterPorIdSpotify(String id);

	Disco salvar(Disco disco);

	List<Disco> obterDiscos(Artista artista);
}
