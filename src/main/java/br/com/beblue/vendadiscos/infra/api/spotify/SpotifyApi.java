package br.com.beblue.vendadiscos.infra.api.spotify;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.beblue.vendadiscos.infra.api.spotify.model.ArtistsItem;
import br.com.beblue.vendadiscos.infra.api.spotify.model.SpotifySearchResponse;

@Service
public class SpotifyApi {

	@Value("${spotify.client_id}")
	private String clientId;

	@Value("${spotify.client_secret}")
	private Object clientSecret;

	private SpotifyToken token;

	@PostConstruct
	public void init() {
		if (token == null) {
			token = gerarToken();
		}
	}

	private SpotifyToken gerarToken() {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + obterCredenciais());
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(map, headers);

		ResponseEntity<SpotifyToken> responseEntity = 
				new RestTemplate().exchange("https://accounts.spotify.com/api/token ", HttpMethod.POST, request, SpotifyToken.class);

		return responseEntity.getBody();
	}

	private String uriArtistsAlbums(String id, int indiceDoDisco) {

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(String.format("https://api.spotify.com/v1/artists/%s/albums", id))
				.queryParam("offset", String.valueOf(indiceDoDisco))
				.queryParam("limit", String.valueOf(indiceDoDisco + 1));
		return uriComponentsBuilder.toUriString();
	}

	private HttpHeaders obterHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, String.format("%s %s", token.getToken_type(), token.getAccess_token()));
		return headers;
	}

	private String uriSearchArtist(String nomeGenero, int offset, int limit) {

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("https://api.spotify.com/v1/search")
				.queryParam("q", String.format("genre:%s", nomeGenero))
				.queryParam("type", "artist")
				.queryParam("offset", String.valueOf(offset))
				.queryParam("limit", String.valueOf(limit));
		return uriComponentsBuilder.toUriString();
	}

	private String obterCredenciais() {

		String credenciais = String.format("%s:%s", this.clientId, this.clientSecret);
		return Base64.encodeBase64String(credenciais.getBytes());
	}

	public List<ArtistsItem> obterArtistas(String genero, int offset, int limit) {
		
		HttpEntity<String> request = new HttpEntity<>(obterHttpHeaders());
		String uriSearchArtist = uriSearchArtist(genero, offset, limit);
		ResponseEntity<SpotifySearchResponse> responseEntity = 
				new RestTemplate().exchange(uriSearchArtist, HttpMethod.GET, request, SpotifySearchResponse.class);

		return responseEntity.getBody().getArtists().getItems().stream().collect(Collectors.toList());
	}

	public AlbumItem obterDisco(String idArtista, int indiceDoDisco) {
		
		HttpEntity<String> request = new HttpEntity<>(obterHttpHeaders());
		String uriArtistsAlbums = uriArtistsAlbums(idArtista, indiceDoDisco);
		ResponseEntity<SpotifyAlbumsResponse> responseEntity = new RestTemplate().exchange(uriArtistsAlbums, HttpMethod.GET, request, SpotifyAlbumsResponse.class);
		return responseEntity.getBody().getItems().get(0);
	}
}
