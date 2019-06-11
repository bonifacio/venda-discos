package br.com.beblue.vendadiscos.infra.api.spotify.model;

public class ArtistsItem {
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ArtistsItem [id=" + id + ", name=" + name + "]";
	}
}
