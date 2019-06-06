package br.com.beblue.vendadiscos.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Venda extends EntityBase {

	@CreatedDate
	private LocalDateTime data;
	
	@OneToMany(mappedBy = "venda", fetch = FetchType.EAGER)
	private List<Item> itens;
}
