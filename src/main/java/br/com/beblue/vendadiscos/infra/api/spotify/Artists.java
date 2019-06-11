package br.com.beblue.vendadiscos.infra.api.spotify;

import java.util.List;

import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistsItem;

public class Artists {

	private List<ArtistsItem> items;

	public List<ArtistsItem> getItems() {
		return items;
	}

	public void setItems(List<ArtistsItem> items) {
		this.items = items;
	}
}
