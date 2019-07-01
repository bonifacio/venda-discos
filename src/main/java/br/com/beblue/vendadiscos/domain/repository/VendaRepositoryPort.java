package br.com.beblue.vendadiscos.domain.repository;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface VendaRepositoryPort {

    Page<Venda> pesquisar(VendaFilter filtro, Pagina pagina, Ordenacao ordenacao);

    Optional<Venda> obterPorId(Long id);

    void registrarVenda(Venda venda);
}
