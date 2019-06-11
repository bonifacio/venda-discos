package br.com.beblue.vendadiscos.domain.repository;

public interface SpotifyRepositoryPort {

	void importarDiscos(int offset, int limit);
}
