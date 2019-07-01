package br.com.beblue.vendadiscos.infra.api.spotify.model;

import java.util.List;

public class DiscoSpotify {

    private String name;

    private List<ArtistaSpotify> artists;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArtistaSpotify> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistaSpotify> artists) {
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
