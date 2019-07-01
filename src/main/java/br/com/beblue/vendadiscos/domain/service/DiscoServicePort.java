package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;
import br.com.beblue.vendadiscos.domain.model.filter.DiscoFilter;
import org.springframework.data.domain.Page;

public interface DiscoServicePort {

    Page<DiscoDTO> pesquisar(DiscoFilter discoFilter, int numeroPagina, int tamanhoPagina);

    DiscoDTO obterPorId(Long id);
}
