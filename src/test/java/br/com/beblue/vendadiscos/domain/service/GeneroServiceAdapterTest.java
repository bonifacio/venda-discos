package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.model.Genero;
import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;
import br.com.beblue.vendadiscos.domain.repository.GeneroRepositoryPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GeneroServiceAdapterTest {

    @Mock
    private GeneroRepositoryPort generoRepository;

    @InjectMocks
    private GeneroServiceAdapter generoService;

    @Test
    public void deveRetornarUmaListaVazia_quandoNaoExistirGenerosCadastrados() {
        List<Genero> generos = Collections.emptyList();
        List<GeneroDTO> generosDTO = Collections.emptyList();
        when(generoRepository.obterTodos()).thenReturn(generos);

        List<GeneroDTO> generosRetornados = generoService.obterTodos();

        verify(generoRepository, times(1)).obterTodos();
        assertNotNull(generosRetornados);
        assertEquals("Tamanho da lista", generos.size(), generosDTO.size());
    }

    @Test
    public void deveRetornarTodosOsGeneros_quandoExistiremGenerosCadastrados() {

        Genero genero = new Genero();
        genero.setId(1L);
        genero.setNome("POP");

        GeneroDTO generoDTO = new GeneroDTO(genero);

        List<Genero> generos = Arrays.asList(genero);
        List<GeneroDTO> generosDTO = Arrays.asList(generoDTO);

        when(generoRepository.obterTodos()).thenReturn(generos);

        List<GeneroDTO> generosRetornados = generoService.obterTodos();

        verify(generoRepository, times(1)).obterTodos();
        assertNotNull(generosRetornados);
        assertEquals("Tamanho da lista", generos.size(), generosDTO.size());
    }
}
