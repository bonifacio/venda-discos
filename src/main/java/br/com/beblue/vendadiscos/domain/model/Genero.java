package br.com.beblue.vendadiscos.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Genero extends EntityBase {

	private String nome;
	
	@OneToMany(mappedBy = "genero")
	private	List<Cashback> cashback;

	public String getNome() {
		return nome;
	}
}
