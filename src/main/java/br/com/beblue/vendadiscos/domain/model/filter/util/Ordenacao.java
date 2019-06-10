package br.com.beblue.vendadiscos.domain.model.filter.util;

public class Ordenacao {

	public enum Direcao {
		ASC, DESC;
	}

	private String campo;
	
	private Direcao direcao;

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
}
