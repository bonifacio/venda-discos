package br.com.beblue.vendadiscos.domain.model;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
public class Venda extends EntityBase {

    private LocalDateTime data;

    @OneToMany(mappedBy = "venda", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Item> itens;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
