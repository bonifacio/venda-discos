package br.com.beblue.vendadiscos.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.infra.repository.ArtistaRepository;
import br.com.beblue.vendadiscos.infra.repository.DiscoRepository;
import br.com.beblue.vendadiscos.infra.repository.GeneroRepository;
import net.bytebuddy.utility.RandomString;

@RunWith(SpringRunner.class)
@SpringBootTest( properties = { "job.autorun.enabled=false" } )
@AutoConfigureMockMvc
public class VendaResourceIntegrationTest {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.registerModules(new Jdk8Module(), new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private DiscoRepository discoRepository;

	@Test
	public void deveRetornarUmaListaDeVendasPaginada() throws Exception {
		
		mockMvc.perform(get("/venda").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content").isArray());
	}
	
	@Test
	public void deveRetornarErro_quandoNaoExistirODiscoInformado() throws Exception {
		
		ItemDTO itemDTO = new ItemDTO(9999999L, 1);
		List<ItemDTO> itemDTOs = Arrays.asList(itemDTO);
		
		mockMvc.perform(post("/venda").contentType(MediaType.APPLICATION_JSON).content(objetoParaString(itemDTOs)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message").exists());
	}
	
	@Test
	public void deveRegistrarAVenda() throws Exception {
		
		Disco disco = inserirDiscoNoRepositorio();
		
		ItemDTO itemDTO = new ItemDTO(disco.getId(), 1);
		List<ItemDTO> itemDTOs = Arrays.asList(itemDTO);
		
		mockMvc.perform(post("/venda").contentType(MediaType.APPLICATION_JSON).content(objetoParaString(itemDTOs)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.itens").isArray())
			.andExpect(jsonPath("$.itens[0].idDisco").value(disco.getId()));
	}
	
	@Test
	public void deveRetornar404_quandAVendaNaoExistir() throws Exception {
		
		mockMvc.perform(get("/venda/999999").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void deveRetornarAVenda() throws Exception {
		
		Disco disco = inserirDiscoNoRepositorio();
		
		ItemDTO itemDTO = new ItemDTO(disco.getId(), 1);
		List<ItemDTO> itemDTOs = Arrays.asList(itemDTO);
		
		MvcResult result = mockMvc.perform(post("/venda").contentType(MediaType.APPLICATION_JSON).content(objetoParaString(itemDTOs)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andReturn();
		
		VendaDTO vendaDTO = stringParaObjeto(result.getResponse().getContentAsString(), VendaDTO.class);
		
		mockMvc.perform(get(String.format("/venda/%s", vendaDTO.getId())).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(vendaDTO.getId()));
	}

	private Disco inserirDiscoNoRepositorio() {
		
		Disco disco = new Disco();
		disco.setIdSpotify(RandomString.make(10));
		disco.setNome("B");
		disco.setPreco(BigDecimal.valueOf(11.5));
		disco.adicionarArtista(inserirArtistaNoRepositorio());
		return discoRepository.save(disco);
	}

	private Artista inserirArtistaNoRepositorio() {
		
		Artista artista = new Artista();
		artista.setIdSpotify(RandomString.make(10));
		artista.setNome("D");
		artista.adicionarGenero(generoRepository.findById(1L).get());
		return artistaRepository.save(artista);
	}

	private String objetoParaString(List<ItemDTO> itemDTOs) throws JsonProcessingException {
		
		return OBJECT_MAPPER.writeValueAsString(itemDTOs);
	}

	private <T> T stringParaObjeto(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		
		return OBJECT_MAPPER.readValue(json, clazz);
	}
}
