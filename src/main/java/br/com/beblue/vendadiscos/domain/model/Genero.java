package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

	public BigDecimal getPercentualCashback() {
		
		Optional<Cashback> cashback = this.cashback.stream()
			.filter(c -> c.getDia().equals(LocalDateTime.now().getDayOfWeek()))
			.findFirst();
		if (cashback.isEmpty()) {
			return BigDecimal.ZERO;
		}
		return cashback.get().getPercentual();
	}
}
