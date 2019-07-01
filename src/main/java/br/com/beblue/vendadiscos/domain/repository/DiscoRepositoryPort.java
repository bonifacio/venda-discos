package br.com.beblue.vendadiscos.domain.repository;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DiscoRepositoryPort {

    Page<Disco> pesquisar(DiscoFilter discoFilter, Pagina pagina, Ordenacao ordenacao);

    Optional<Disco> obterPorId(Long id);

    Optional<Disco> obterPorIdSpotify(String id);

    Disco salvar(Disco disco);

    List<Disco> obterDiscos(Artista artista);
}
