package br.com.KevinVSantos.SimpleLibrary.repository;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenreRepository extends JpaRepository<Genre, String> {
}
