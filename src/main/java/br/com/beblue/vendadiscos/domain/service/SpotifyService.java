package br.com.beblue.vendadiscos.domain.service;

import java.util.List;
import java.util.Optional;
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

import br.com.beblue.vendadiscos.domain.model.Disco;

@Service
public class SpotifyService {

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
		
		ResponseEntity<SpotifyToken> responseEntity = new RestTemplate().exchange("https://accounts.spotify.com/api/token ", HttpMethod.POST, request, SpotifyToken.class);
		return responseEntity.getBody();
	}

	public List<Disco> obterDiscosPorGenero(String nomeGenero) {
		
		
		List<String> ids = obterIdsArtistas(nomeGenero);
		return ids.stream().map(id -> {
			
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(String.format("https://api.spotify.com/v1/artists/%s/albums", id))
					.queryParam("offset", "0")
					.queryParam("limit", "1");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, String.format("%s %s", token.getToken_type(), token.getAccess_token()));
			HttpEntity<String> request = new HttpEntity<>(headers);
			ResponseEntity<SpotifyAlbumsResponse> responseEntity = new RestTemplate().exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, request, SpotifyAlbumsResponse.class);
			
			Optional<AlbumItem> item = responseEntity.getBody().getItems().stream().findFirst();
			AlbumItem albumItem = item.get();
			
			Disco disco = new Disco();
			disco.setArtistas(albumItem.getArtists().stream().map(ArtistsItem::getName).collect(Collectors.joining(", ")));
			disco.setNome(albumItem.getName());
			return disco;
		})
		.collect(Collectors.toList());
	}

	private List<String> obterIdsArtistas(String nomeGenero) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("https://api.spotify.com/v1/search")
				.queryParam("q", String.format("genre:%s", nomeGenero))
				.queryParam("type", "artist")
				.queryParam("offset", "0")
				.queryParam("limit", "50");
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, String.format("%s %s", token.getToken_type(), token.getAccess_token()));
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<SpotifySearchResponse> responseEntity = new RestTemplate().exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET, request, SpotifySearchResponse.class);
		return responseEntity.getBody().getArtists().getItems().stream().map(ArtistsItem::getId).collect(Collectors.toList());
	}

	private String obterCredenciais() {
		
		String credenciais = String.format("%s:%s", this.clientId, this.clientSecret);
		return Base64.encodeBase64String(credenciais.getBytes());
	}

}
