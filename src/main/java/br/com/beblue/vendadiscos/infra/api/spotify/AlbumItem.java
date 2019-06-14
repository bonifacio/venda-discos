package br.com.beblue.vendadiscos.infra.api.spotify;

import java.util.List;

import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistsItem;

public class AlbumItem {

	private String name;
	
	private List<ArtistsItem> artists;

	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ArtistsItem> getArtists() {
		return artists;
	}

	public void setArtists(List<ArtistsItem> artists) {
		this.artists = artists;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}