package br.com.beblue.vendadiscos.domain.service;

import br.com.beblue.vendadiscos.domain.exception.BusinessException;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Item;
import br.com.beblue.vendadiscos.domain.model.Venda;
import br.com.beblue.vendadiscos.domain.model.Venda_;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;
import br.com.beblue.vendadiscos.domain.model.dto.VendaDTO;
import br.com.beblue.vendadiscos.domain.model.dto.converter.VendaConverter;
import br.com.beblue.vendadiscos.domain.model.filter.VendaFilter;
import br.com.beblue.vendadiscos.domain.model.filter.util.Ordenacao;
import br.com.beblue.vendadiscos.domain.model.filter.util.Pagina;
import br.com.beblue.vendadiscos.domain.repository.DiscoRepositoryPort;
import br.com.beblue.vendadiscos.domain.repository.VendaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Validation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VendaServiceAdapter implements VendaServicePort {

    private VendaRepositoryPort vendaRepository;

    private DiscoRepositoryPort discoRepository;

    @Autowired
    public VendaServiceAdapter(
            VendaRepositoryPort vendaRepository,
            DiscoRepositoryPort discoRepository) {

        this.vendaRepository = vendaRepository;
        this.discoRepository = discoRepository;
    }

    @Override
    public Page<VendaDTO> pesquisar(VendaFilter filtro, int numeroPagina, int tamanhoPagina) {

        Pagina pagina = new Pagina(numeroPagina, tamanhoPagina);
        Ordenacao ordenacao = new Ordenacao(Venda_.DATA, Ordenacao.Direcao.DESC);
        Page<Venda> vendasPaginadas = vendaRepository.pesquisar(filtro, pagina, ordenacao);
        return new PageImpl<>(
                VendaConverter.paraDTO(vendasPaginadas.getContent()),
                vendasPaginadas.getPageable(),
                vendasPaginadas.getTotalElements());
    }

    @Override
    public VendaDTO obterPorId(Long id) {

        Optional<Venda> venda = vendaRepository.obterPorId(id);
        if (!venda.isPresent()) {
            return null;
        }
        return VendaConverter.paraDTO(venda.get());
    }

    @Override
    @Transactional
    public VendaDTO registrarVenda(final List<ItemDTO> itensDTO) {

        validarItensDaVenda(itensDTO);
        Venda venda = new Venda();
        List<Item> itens = itensDTO.stream().map(itemDTO -> {
            Item item = new Item();
            item.setQuantidade(itemDTO.getQuantidade());
            Optional<Disco> optionalDisco = discoRepository.obterPorId(itemDTO.getIdDisco());
            if (!optionalDisco.isPresent()) {
                throw new BusinessException(String.format("Disco %s não encontrado.", itemDTO.getIdDisco()));
            }
            item.setDisco(optionalDisco.get());
            item.setVenda(venda);
            return item;
        })
                .collect(Collectors.toList());
        venda.setItens(itens);
        venda.setData(LocalDateTime.now());
        vendaRepository.registrarVenda(venda);
        return new VendaDTO(venda);
    }

    private void validarItensDaVenda(final List<ItemDTO> itensDTO) {

        if (CollectionUtils.isEmpty(itensDTO)) {
            throw new BusinessException("Para realizar uma compra é necessário escolher pelo menos um item.");
        }

        Set<String> erros = itensDTO.stream()
                .map(Validation.buildDefaultValidatorFactory().getValidator()::validate)
                .flatMap(Set::stream)
                .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(erros)) {
            throw new BusinessException(String.join(", ", erros));
        }
    }
}
