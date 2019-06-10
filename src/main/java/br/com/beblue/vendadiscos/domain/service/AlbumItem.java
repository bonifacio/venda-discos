package br.com.beblue.vendadiscos.domain.service;

import java.util.List;

public class AlbumItem {

	private String name;
	
	private List<ArtistsItem> artists;

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
}
