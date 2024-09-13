package br.com.KevinVSantos.SimpleLibrary.repository;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
