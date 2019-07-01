package br.com.beblue.vendadiscos.domain.model.dto;

import br.com.beblue.vendadiscos.domain.model.Artista;
import br.com.beblue.vendadiscos.domain.model.Disco;
import br.com.beblue.vendadiscos.domain.model.Genero;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel("Disco")
public class DiscoDTO {

    private Long id;
    private String nome;
    private Set<String> artistas;
    private Set<String> generos;
    private BigDecimal preco;
    private BigDecimal cashback;

    public DiscoDTO() {
    }

    public DiscoDTO(Disco disco) {
        id = disco.getId();
        nome = disco.getNome();
        artistas = disco.getArtistas().stream().map(Artista::getNome).collect(Collectors.toSet());
        generos = disco.getArtistas().stream().map(Artista::getGeneros).flatMap(Set::stream).map(Genero::getNome).collect(Collectors.toSet());
        preco = disco.getPreco();
        cashback = disco.getCashback();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getArtistas() {
        return artistas;
    }

    public Set<String> getGeneros() {
        return generos;
    }

    public BigDecimal getCashback() {
        return cashback;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
