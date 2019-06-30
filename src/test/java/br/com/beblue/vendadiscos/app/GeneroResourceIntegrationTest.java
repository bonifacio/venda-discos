package br.com.beblue.vendadiscos.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest( properties = { "job.autorun.enabled=false" } )
@AutoConfigureMockMvc
public class GeneroResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveRetornarUmaListaDeGeneros() throws Exception {

		mockMvc.perform(get("/genero").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$[0].id").exists())
			.andExpect(jsonPath("$[0].nome").exists());
	}
}
