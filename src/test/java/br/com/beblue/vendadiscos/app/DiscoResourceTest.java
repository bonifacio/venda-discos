package br.com.beblue.vendadiscos.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.dto.DiscoDTO;
import br.com.beblue.vendadiscos.infra.repository.DiscoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"job.autorun.enabled=false"})
public class DiscoResourceTest {
	
	@LocalServerPort
	private int port;
	
	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	@Autowired
	private DiscoRepository discoRepository;

	@Test
	public void deveRetornarUmaListaDeDiscos() {
		
		Object page = testRestTemplate.getForEntity(URL.montar(port, "/disco"), Object.class).getBody();
		assertNotNull(page);
	}
	
	@Test
	public void deveRetornarUmDisco() {
		
		Disco disco = new Disco();
		disco.setIdSpotify("X");
		disco.setNome("Disco teste");
		disco.setPreco(BigDecimal.valueOf(100));
		discoRepository.save(disco);
		
		DiscoDTO entity = testRestTemplate.getForObject(URL.montar(port, String.format("/disco/%s", disco.getId())), DiscoDTO.class);
		assertNotNull(entity);
	}
	
	@Test
	public void deveRetornar404_quandoNaoExistirODiscoPesquisado() {
		ResponseEntity<DiscoDTO> entity = testRestTemplate.getForEntity(URL.montar(port, "/disco/9999999"), DiscoDTO.class);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
	}

}
