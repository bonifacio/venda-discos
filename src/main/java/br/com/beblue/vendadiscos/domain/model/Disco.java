package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.CollectionUtils;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Disco extends EntityBase {

	@NotBlank
	@Column(unique = true)
	private String idSpotify;

	@Size(max = 150)
	@NotBlank
	private String nome;

	@NotNull
	private BigDecimal preco;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Artista> artistas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Set<Artista> getArtistas() {
		return Collections.unmodifiableSet(artistas);
	}

	public void adicionarArtista(Artista artista) {
		if (CollectionUtils.isEmpty(artistas)) {
			artistas = new HashSet<>();
		}
		artistas.add(artista);
	}

	public BigDecimal getCashback() {
		Stream<BigDecimal> percentuais = getGeneros().stream().map(Genero::getPercentualCashback);
		Optional<BigDecimal> maiorPercentual = percentuais.max(Comparator.naturalOrder());
		return maiorPercentual.orElse(BigDecimal.ZERO);
	}

	public String getIdSpotify() {
		return idSpotify;
	}

	public void setIdSpotify(String idSpotify) {
		this.idSpotify = idSpotify;
	}

	public boolean possuiMaisDeUmGenero() {
		return getGeneros().size() > 1;
	}

	public Set<Genero> getGeneros() {
		if (CollectionUtils.isEmpty(artistas)) {
			return Collections.emptySet();
		}
		return artistas.stream().map(Artista::getGeneros).flatMap(Set::stream).collect(Collectors.toSet());
	}

	public boolean possuiApenasUmArtista() {
		if (CollectionUtils.isEmpty(artistas)) {
			return true;
		}
		return artistas.size() == 1;
	}
}
