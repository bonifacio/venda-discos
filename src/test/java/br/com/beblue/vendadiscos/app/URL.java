package br.com.beblue.vendadiscos.app;

public class URL {
	public static String montar(int port, String recurso) {
		return String.format("http://localhost:%s%s", port, recurso);
	}
}
