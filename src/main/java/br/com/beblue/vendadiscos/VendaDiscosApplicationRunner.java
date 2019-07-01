package br.com.beblue.vendadiscos;

import br.com.beblue.vendadiscos.domain.service.ImportacaoDiscosServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class VendaDiscosApplicationRunner implements CommandLineRunner {

    private final ImportacaoDiscosServicePort importacaoDiscosService;

    @Autowired
    public VendaDiscosApplicationRunner(ImportacaoDiscosServicePort importacaoDiscosService) {
        this.importacaoDiscosService = importacaoDiscosService;
    }

    @Override
    public void run(String... args) {
        importacaoDiscosService.importar();
    }
}
