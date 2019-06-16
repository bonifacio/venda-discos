package br.com.beblue.vendadiscos.infra.api.spotify.model;

import java.util.List;

public class ListaArtistaSpotify {

	private List<ArtistaSpotify> items;

	public List<ArtistaSpotify> getItems() {
		return items;
	}

	public void setItems(List<ArtistaSpotify> items) {
		this.items = items;
	}
}
