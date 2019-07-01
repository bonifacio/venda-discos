package br.com.beblue.vendadiscos.app;

import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.service.VendaServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaResource {

    private final VendaServicePort vendaService;

    @Autowired
    public VendaResource(VendaServicePort vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public Page<VendaDTO> pesquisar(
            @RequestParam(defaultValue = "0") int numeroPagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina,
            VendaFilter filtro) {

        return vendaService.pesquisar(filtro, numeroPagina, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> obterPorId(@PathVariable Long id) {

        VendaDTO venda = vendaService.obterPorId(id);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venda);
    }

    @PostMapping
    public ResponseEntity<VendaDTO> registrarVenda(@RequestBody List<ItemDTO> itens) {

        return ResponseEntity.ok(vendaService.registrarVenda(itens));
    }
}
