package br.com.beblue.vendadiscos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.beblue.vendadiscos.domain.service.ImportacaoDiscosServicePort;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class VendaDiscosApplicationRunner implements CommandLineRunner {

	private ImportacaoDiscosServicePort importacaoDiscosService;

	@Autowired
	public VendaDiscosApplicationRunner(ImportacaoDiscosServicePort importacaoDiscosService) {
		this.importacaoDiscosService = importacaoDiscosService;
	}

	@Override
	public void run(String... args) throws Exception {
		importacaoDiscosService.importar();
	}
}
