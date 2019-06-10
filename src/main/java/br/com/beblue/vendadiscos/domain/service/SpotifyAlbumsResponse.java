package br.com.beblue.vendadiscos.domain.service;

import java.util.List;

public class SpotifyAlbumsResponse {

	private List<AlbumItem> items;

	public List<AlbumItem> getItems() {
		return items;
	}

	public void setItems(List<AlbumItem> items) {
		this.items = items;
	}
}
