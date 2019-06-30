package br.com.beblue.vendadiscos.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.infra.repository.DiscoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest( properties = { "job.autorun.enabled=false" } )
@AutoConfigureMockMvc
public class DiscoResourceIntegrationTest {
	
	@Autowired
	private DiscoRepository discoRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveRetornarUmaListaDeDiscosPaginada() throws Exception {
		
		mockMvc.perform(get("/disco").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content").isArray());
	}
	
	@Test
	public void deveRetornarUmDisco() throws Exception {
		
		Disco disco = new Disco();
		disco.setIdSpotify("X");
		disco.setNome("Disco teste");
		disco.setPreco(BigDecimal.valueOf(100.1));
		discoRepository.save(disco);
		
		mockMvc.perform(get(String.format("/disco/%s", disco.getId())).accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(disco.getId()))
			.andExpect(jsonPath("$.nome").value(disco.getNome()))
			.andExpect(jsonPath("$.preco").value(disco.getPreco()))
			.andExpect(jsonPath("$.artistas").isArray())
			.andExpect(jsonPath("$.generos").isArray());
	}
	
	@Test
	public void deveRetornar404_quandoNaoExistirODiscoPesquisado() throws Exception {
		
		mockMvc.perform(get(String.format("/disco/%s", 999999)).accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isNotFound());
	}
}
