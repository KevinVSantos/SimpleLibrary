package br.com.KevinVSantos.SimpleLibrary.repository;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, String> {
    List<Book> findByMainGenreTitle(String mainGenreTitle);
    List<Book> findBySubGenreTitle(String subGenreTitle);
    List<Book> findByAuthor(String author);
}
