package br.com.beblue.vendadiscos.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.beblue.vendadiscos.domain.model.Item;
import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.Venda_;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.dto.converter.VendaConverter;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.VendaRepositoryPort;

@Service
public class VendaServiceAdapter implements VendaServicePort {
	
	private VendaRepositoryPort vendaRepository;
	
	private DiscoRepositoryPort discoRepository;
	
	@Autowired
	public VendaServiceAdapter(VendaRepositoryPort vendaRepository, DiscoRepositoryPort discoRepository) {
		this.vendaRepository = vendaRepository;
		this.discoRepository = discoRepository;
	}

	@Override
	public Page<VendaDTO> pesquisar(VendaFilter filtro, int numeroPagina, int tamanhoPagina) {
		
		Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
		Ordenacao ordenacao = new Ordenacao(Venda_.data.getName(), Ordenacao.Direcao.DESC);
		Page<Venda> discosPaginados = vendaRepository.pesquisar(filtro, pagina, ordenacao);
		return new PageImpl<VendaDTO>(
				VendaConverter.paraDTO(discosPaginados.getContent()),
				discosPaginados.getPageable(),
				discosPaginados.getTotalElements());
	}

	@Override
	public VendaDTO obterPorId(Long id) {
		
		Optional<Venda> venda = vendaRepository.obterPorId(id);
		if (!venda.isPresent()) {
			return null;
		}
		return VendaConverter.paraDTO(venda.get());
	}

	@Override
	@Transactional
	public VendaDTO registrarVenda(final List<ItemDTO> itensDTO) {
		
		Venda venda = new Venda();
		List<Item> itens = itensDTO.stream().map(itemDTO -> {
			Item item = new Item();
			item.setQuantidade(itemDTO.getQuantidade());
			item.setDisco(discoRepository.obterPorId(itemDTO.getIdDisco()).get());
			item.setVenda(venda);
			return item;
		})
		.collect(Collectors.toList());
		venda.setItens(itens);
		venda.setData(LocalDateTime.now());
		vendaRepository.registrarVenda(venda);
		return new VendaDTO(venda);
	}
}
