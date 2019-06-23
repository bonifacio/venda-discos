package br.com.beblue.vendadiscos.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.beblue.vendadiscos.domain.exception.BusinessException;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Item;
import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.VendaRepositoryPort;

@RunWith(MockitoJUnitRunner.class)
public class VendaServiceAdapterTest {

	@Mock
	private VendaRepositoryPort vendaRepository;
	
	@Mock
	private DiscoRepositoryPort discoRepository;
	
	@InjectMocks
	private VendaServiceAdapter vendaService;
	
	@Test
	public void deveRetornarAPrimeiraPaginaComOTamanhoPadrao_quandoNumeroPaginaMenorQueOMinimo() {
		
		int numeroPagina = -1;
		int tamanhoPagina = 20;
		Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
		
		List<Venda> discos = montarListaVendas(pagina.getTamanho());
		Page<Venda> page = new PageImpl<Venda>(discos);
		when(vendaRepository.pesquisar(any(), eq(pagina), any())).thenReturn(page);
		
		Page<VendaDTO> retornoNumeroPaginaMenorQueZero = vendaService.pesquisar(null, numeroPagina, tamanhoPagina);
		assertNotNull(retornoNumeroPaginaMenorQueZero);
		assertEquals("Número da página", pagina.getNumero(), retornoNumeroPaginaMenorQueZero.getNumber());
		assertEquals("Tamanho da página", pagina.getTamanho(), retornoNumeroPaginaMenorQueZero.getNumberOfElements());
	}


	@Test
	public void deveRetornarAPrimeiraPaginaComOTamanhoPadrao_quandoTamanhoPaginaMenorQueOMinimo() {
		
		int numeroPagina = 0;
		int tamanhoPagina = 9;
		Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
		List<Venda> discos = montarListaVendas(pagina.getTamanho());
		Page<Venda> page = new PageImpl<Venda>(discos);
		
		when(vendaRepository.pesquisar(any(), eq(pagina), any())).thenReturn(page);
		
		Page<VendaDTO> retornoNumeroPaginaMenorQueZero = vendaService.pesquisar(null, numeroPagina, tamanhoPagina);
		assertNotNull(retornoNumeroPaginaMenorQueZero);
		assertEquals("Número da página", pagina.getNumero(), retornoNumeroPaginaMenorQueZero.getNumber());
		assertEquals("Tamanho da página", pagina.getTamanho(), retornoNumeroPaginaMenorQueZero.getNumberOfElements());
	}
	
	@Test
	public void deveRetornarNulo_quandoNaoExistirDiscoComOIdPassado() {
		
		when(vendaRepository.obterPorId(any())).thenReturn(Optional.empty());
		
		VendaDTO vendaDTO = vendaService.obterPorId(1L);
		
		assertNull(vendaDTO);
	}
	
	@Test
	public void deveRetornarVenda_quandoExistirDiscoComOIdPassado() {
		
		long id = 1L;
		Venda venda = montarVenda(id);
		venda.setId(id);
		VendaDTO vendaDTO = new VendaDTO(venda);
		
		when(vendaRepository.obterPorId(eq(id))).thenReturn(Optional.of(venda));
		
		VendaDTO vendaDTOretornada = vendaService.obterPorId(id);
		
		assertNotNull(vendaDTOretornada);
		assertEquals("id", vendaDTO.getId(), vendaDTOretornada.getId());
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarUmaExcessao_quandoAListaDeItensForVazia() {
		
		vendaService.registrarVenda(Arrays.asList());
	}
	
	@Test
	public void deve() {
		
		when(discoRepository.obterPorId(any())).thenReturn(Optional.of(new Disco()));
	}
	
	private List<Venda> montarListaVendas(int tamanhoPagina) {
	
		List<Venda> vendas = new ArrayList<>();
		for (int i = 1; i <= tamanhoPagina; i++) {
			Venda disco = montarVenda(i);
			vendas.add(disco);
		}
		return vendas;
	}

	private Venda montarVenda(long i) {
		
		Venda venda = new Venda();
		venda.setId(i);
		Item item = new Item();
		item.setQuantidade(1);
		Disco disco = new Disco();
		disco.setPreco(new BigDecimal(100));
		item.setDisco(disco);
		venda.setItens(Arrays.asList(item));
		return venda;
	}
}
