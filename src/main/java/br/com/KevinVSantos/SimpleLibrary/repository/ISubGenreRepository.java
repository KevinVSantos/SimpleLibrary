package br.com.KevinVSantos.SimpleLibrary.repository;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.SubGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubGenreRepository extends JpaRepository<SubGenre, String> {
}
