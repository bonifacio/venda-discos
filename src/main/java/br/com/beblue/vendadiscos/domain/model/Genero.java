package br.com.beblue.vendadiscos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.util.CollectionUtils;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

@Entity
public class Genero extends EntityBase {

	private String nome;
	
	@OneToMany(mappedBy = "genero", fetch = FetchType.EAGER)
	private	List<Cashback> cashback;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPercentualCashback() {
		
		if (CollectionUtils.isEmpty(cashback)) {
			return BigDecimal.ZERO;
		}
		Optional<Cashback> cashback = this.cashback.stream()
			.filter(c -> c.getDia().equals(LocalDateTime.now().getDayOfWeek()))
			.findFirst();
		if (!cashback.isPresent()) {
			return BigDecimal.ZERO;
		}
		return cashback.get().getPercentual();
	}
}
