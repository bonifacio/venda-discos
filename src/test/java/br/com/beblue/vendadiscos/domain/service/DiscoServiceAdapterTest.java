package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.MockFactory;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscoServiceAdapterTest {

    @Mock
    private DiscoRepositoryPort discoRepository;

    @InjectMocks
    private DiscoServiceAdapter discoService;

    @Test
    public void deveRetornarAPrimeiraPaginaComOTamanhoPadrao_quandoNumeroPaginaMenorQueOMinimo() {

        int numeroPagina = -1;
        int tamanhoPagina = 20;
        Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);

        List<Disco> discos = MockFactory.montarListaDiscos(pagina.getTamanho());
        Page<Disco> page = new PageImpl<>(discos);
        when(discoRepository.pesquisar(any(), eq(pagina), any())).thenReturn(page);

        Page<DiscoDTO> retornoNumeroPaginaMenorQueZero = discoService.pesquisar(null, numeroPagina, tamanhoPagina);
        assertNotNull(retornoNumeroPaginaMenorQueZero);
        assertEquals("Número da página", pagina.getNumero(), retornoNumeroPaginaMenorQueZero.getNumber());
        assertEquals("Tamanho da página", pagina.getTamanho(), retornoNumeroPaginaMenorQueZero.getNumberOfElements());
    }


    @Test
    public void deveRetornarAPrimeiraPaginaComOTamanhoPadrao_quandoTamanhoPaginaMenorQueOMinimo() {

        int numeroPagina = 0;
        int tamanhoPagina = 9;
        Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
        List<Disco> discos = MockFactory.montarListaDiscos(pagina.getTamanho());
        Page<Disco> page = new PageImpl<>(discos);

        when(discoRepository.pesquisar(any(), eq(pagina), any())).thenReturn(page);

        Page<DiscoDTO> retornoNumeroPaginaMenorQueZero = discoService.pesquisar(null, numeroPagina, tamanhoPagina);
        assertNotNull(retornoNumeroPaginaMenorQueZero);
        assertEquals("Número da página", pagina.getNumero(), retornoNumeroPaginaMenorQueZero.getNumber());
        assertEquals("Tamanho da página", pagina.getTamanho(), retornoNumeroPaginaMenorQueZero.getNumberOfElements());
    }

    @Test
    public void deveRetornarNulo_quandoNaoExistirDiscoComOIdPassado() {

        when(discoRepository.obterPorId(any())).thenReturn(Optional.empty());

        DiscoDTO discoDTO = discoService.obterPorId(1L);

        assertNull(discoDTO);
    }

    @Test
    public void deveRetornarDisco_quandoExistirDiscoComOIdPassado() {

        long id = 1L;
        Disco disco = MockFactory.montarDisco(id);
        disco.setId(id);
        DiscoDTO discoDTO = new DiscoDTO(disco);

        when(discoRepository.obterPorId(eq(id))).thenReturn(Optional.of(disco));

        DiscoDTO discoDTOretornado = discoService.obterPorId(id);

        assertNotNull(discoDTOretornado);
        assertEquals("id", discoDTO.getId(), discoDTOretornado.getId());
    }
}
