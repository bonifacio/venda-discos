package br.com.beblue.vendadiscos.domain.model;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
public class Genero extends EntityBase {

    private String nome;

    @OneToMany(mappedBy = "genero", fetch = FetchType.EAGER)
    private List<Cashback> cashback;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPercentualCashback() {

        if (CollectionUtils.isEmpty(getCashback())) {
            return BigDecimal.ZERO;
        }
        Optional<Cashback> cashbackDoDia = this.getCashback().stream()
                .filter(c -> c.getDia().equals(LocalDateTime.now().getDayOfWeek()))
                .findFirst();
        if (!cashbackDoDia.isPresent()) {
            return BigDecimal.ZERO;
        }
        return cashbackDoDia.get().getPercentual();
    }

    public List<Cashback> getCashback() {
        return cashback;
    }

    public void setCashback(List<Cashback> cashback) {
        this.cashback = cashback;
    }
}
