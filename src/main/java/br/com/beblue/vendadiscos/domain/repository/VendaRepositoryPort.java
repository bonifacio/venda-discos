package br.com.beblue.vendadiscos.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;

public interface VendaRepositoryPort {

	Page<Venda> pesquisar(VendaFilter filtro, Pagina pagina, Ordenacao ordenacao);

	Optional<Venda> obterPorId(Long id);

	void registrarVenda(Venda venda);
}
