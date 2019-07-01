package br.com.beblue.vendadiscos.domain.model.filter.util;

public class Pagina {

    private static final int TAMANHO_MINIMO_PAGINA = 10;

    private static final int NUMERO_MINIMO_PAGINA = 0;

    private int numero = NUMERO_MINIMO_PAGINA;

    private int tamanho = TAMANHO_MINIMO_PAGINA;

    public Pagina(int numero, int tamanho) {
        if (numero > NUMERO_MINIMO_PAGINA) {
            this.numero = numero;
        }
        if (tamanho > TAMANHO_MINIMO_PAGINA) {
            this.tamanho = tamanho;
        }
    }

    public int getNumero() {
        return numero;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
        result = prime * result + tamanho;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagina other = (Pagina) obj;
        if (numero != other.numero)
            return false;
        return tamanho == other.tamanho;
    }


}
