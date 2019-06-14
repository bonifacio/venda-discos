package br.com.beblue.vendadiscos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.beblue.vendadiscos.domain.service.SpotifyServicePort;

@Component
@Profile("!test")
public class VendaDiscosApplicationRunner implements CommandLineRunner {
	
	private SpotifyServicePort spotifyService;
	
	@Autowired
	public VendaDiscosApplicationRunner(SpotifyServicePort spotifyService) {
		this.spotifyService = spotifyService;
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		spotifyService.importarDiscos();
	}
}
