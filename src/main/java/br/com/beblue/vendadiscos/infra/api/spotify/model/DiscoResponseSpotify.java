package br.com.beblue.vendadiscos.infra.api.spotify.model;

import java.util.List;

public class DiscoResponseSpotify {

    private List<DiscoSpotify> items;

    public List<DiscoSpotify> getItems() {
        return items;
    }

    public void setItems(List<DiscoSpotify> items) {
        this.items = items;
    }
}
