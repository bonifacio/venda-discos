package br.com.beblue.vendadiscos.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.beblue.vendadiscos.domain.model.dto.GeneroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"job.autorun.enabled=false"})
public class GeneroResourceTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Test
	public void deveRetornarUmaListaDeGeneros() {

		GeneroDTO[] generoDTOs = testRestTemplate.getForEntity(URL.montar(port, "/genero"), GeneroDTO[].class).getBody();
		assertNotNull(generoDTOs);
	}
}
