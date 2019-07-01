package br.com.beblue.vendadiscos.domain.model;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artista extends EntityBase {

    @NotBlank
    @Column(unique = true)
    private String idSpotify;

    @Size(max = 150)
    @NotBlank
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genero> generos;

    public String getIdSpotify() {
        return idSpotify;
    }

    public void setIdSpotify(String idSpotify) {
        this.idSpotify = idSpotify;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Genero> getGeneros() {
        return Collections.unmodifiableSet(generos);
    }

    public void adicionarGenero(Genero genero) {
        if (CollectionUtils.isEmpty(generos)) {
            generos = new HashSet<>();
        }
        generos.add(genero);
    }
}
