package br.com.beblue.vendadiscos.domain.model;

import br.com.beblue.vendadiscos.domain.model.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Item extends EntityBase {

    @ManyToOne
    @NotNull
    private Venda venda;

    @OneToOne
    @NotNull
    private Disco disco;

    @NotNull
    private Integer quantidade;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private BigDecimal cashback;

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
        this.valor = disco.getPreco().multiply(new BigDecimal(quantidade));
        this.cashback = disco.getCashback();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getCashback() {
        return cashback;
    }
}
