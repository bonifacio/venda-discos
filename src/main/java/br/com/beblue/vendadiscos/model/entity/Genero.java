package br.com.beblue.vendadiscos.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "genero")
	private	List<Cashback> cashback;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
