package br.com.beblue.vendadiscos.infra.api.spotify.model;

import br.com.beblue.vendadiscos.infra.api.spotify.Artists;

public class SpotifySearchResponse {

	private Artists artists;

	public Artists getArtists() {
		return artists;
	}

	public void setArtists(Artists artists) {
		this.artists = artists;
	}
}
