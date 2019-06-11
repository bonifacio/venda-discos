package br.com.beblue.vendadiscos.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Artista extends EntityBase {

	private String idSpotify;
	
	private String nome;
	
	@ManyToMany(mappedBy = "artistas")
	private List<Disco> discos;
	
	@ManyToMany
	private List<Genero> genero;
}
