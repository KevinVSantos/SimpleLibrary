package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import br.com.KevinVSantos.SimpleLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController extends AbstractController<String, BookService, Book> {

    @Autowired
    private BookService service;

    @GetMapping("/genre/{genreTitle}")
    public ResponseEntity getByGenre(@PathVariable("genreTitle") String genreTitle){

        return ResponseEntity.ok(service.findByGenre(genreTitle));
    }

    @GetMapping("/subgenre/{subgenreTitle}")
    public ResponseEntity getBySubGenre(@PathVariable("subgenreTitle") String subgenreTitle){

        return ResponseEntity.ok(service.findBySubGenre(subgenreTitle));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity getByAuthor(@PathVariable("author") String author){

        return ResponseEntity.ok(service.findByAuthor(author));
    }
}
