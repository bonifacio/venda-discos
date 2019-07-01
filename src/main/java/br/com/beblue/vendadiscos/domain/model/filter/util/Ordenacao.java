package br.com.beblue.vendadiscos.domain.model.filter.util;

public class Ordenacao {

    private final String campo;
    private final Direcao direcao;

    public Ordenacao(String campo) {
        this.campo = campo;
        this.direcao = Direcao.ASC;
    }

    public Ordenacao(String campo, Direcao direcao) {
        this.campo = campo;
        this.direcao = direcao;
    }

    public String getCampo() {
        return campo;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public enum Direcao {
        ASC, DESC
    }
}
