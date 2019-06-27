package br.com.beblue.vendadiscos.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

import br.com.beblue.vendadiscos.MockFactory;
import br.com.beblue.vendadiscos.domain.exception.BusinessException;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
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

		List<Venda> discos = MockFactory.montarListaVendas(pagina.getTamanho());
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
		List<Venda> discos = MockFactory.montarListaVendas(pagina.getTamanho());
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
		Venda venda = MockFactory.montarVenda(id);
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

	@Test(expected = BusinessException.class)
	public void deveLancarUmaExcecao_quandoOIdDiscoDePeloMenosUmDosItensForNulo() {

		ItemDTO itemDTO = MockFactory.montarItemDTO();
		itemDTO.setIdDisco(null);
		vendaService.registrarVenda(Arrays.asList(itemDTO));
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarUmaExcecao_quandoAQuantidadeDePeloMenosUmDosItensForNulo() {

		ItemDTO itemDTO = MockFactory.montarItemDTO();
		itemDTO.setQuantidade(null);
		vendaService.registrarVenda(Arrays.asList(itemDTO));
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarUmaExcecao_quandoAQuantidadeDePeloMenosUmDosItensForMenorQueUm() {

		ItemDTO itemDTO = MockFactory.montarItemDTO();
		itemDTO.setQuantidade(0);
		vendaService.registrarVenda(Arrays.asList(itemDTO));
	}
	
	@Test(expected = BusinessException.class)
	public void deveLancarUmaExcecao_quandoOIdDiscoNaoExistirNoRepositorio() {

		ItemDTO itemDTO = MockFactory.montarItemDTO();
		itemDTO.setQuantidade(1);
		itemDTO.setIdDisco(999L);
		when(discoRepository.obterPorId(itemDTO.getIdDisco())).thenReturn(Optional.empty());
		vendaService.registrarVenda(Arrays.asList(itemDTO));
	}
	
	@Test
	public void deveRegistrarNoItemOPercentualDoDiaParaDeterminadoGenero() {
		
		ItemDTO itemDTO = MockFactory.montarItemDTO();
		itemDTO.setQuantidade(1);
		itemDTO.setIdDisco(1L);
		
		Disco disco = MockFactory.montarDisco(itemDTO.getIdDisco());
		
		when(discoRepository.obterPorId(itemDTO.getIdDisco())).thenReturn(Optional.of(disco));
		
		doNothing().when(vendaRepository).registrarVenda(any(Venda.class));
		
		VendaDTO vendaDTO = vendaService.registrarVenda(Arrays.asList(itemDTO));
		assertNotNull(vendaDTO);
		assertEquals(disco.getCashback(), vendaDTO.getItens().get(0).getCashback());
	}
}
