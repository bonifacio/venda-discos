package br.com.beblue.vendadiscos.domain.model.filter.util;

public class Pagina {

	private int numero;
	
	private int tamanho;

	public Pagina(int numero, int tamanho) {
		this.numero = numero;
		this.tamanho = tamanho;		
	}

	public int getNumero() {
		return numero;
	}
	
	public int getTamanho() {
		return tamanho;
	}
}
