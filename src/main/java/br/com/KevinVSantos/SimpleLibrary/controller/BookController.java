package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.config.CachingConfig;
import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import br.com.KevinVSantos.SimpleLibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController extends AbstractController<String, BookService, Book> {

    @Autowired
    private BookService service;

    @GetMapping("/genre/{genreTitle}")
    @Operation(description = "Busca todos os livros filtrando pelo gênero.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Lista das entidades encontradas")
    )
    @Cacheable(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, key = "{ #root.methodName, #genreTitle }")
    public ResponseEntity<List<Book>> getByGenre(@PathVariable("genreTitle") String genreTitle){
        return ResponseEntity.ok(service.findByGenre(genreTitle));
    }

    @GetMapping("/subgenre/{subgenreTitle}")
    @Operation(description = "Busca todos os livros filtrando pelo subgênero.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Lista das entidades encontradas")
    )
    @Cacheable(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, key = "{ #root.methodName, #subgenreTitle}")
    public ResponseEntity<List<Book>> getBySubGenre(@PathVariable("subgenreTitle") String subgenreTitle){
        return ResponseEntity.ok(service.findBySubGenre(subgenreTitle));
    }

    @GetMapping("/author/{author}")
    @Operation(description = "Busca todos os livros filtrando pelo autor.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Lista das entidades encontradas")
    )
    @Cacheable(cacheResolver = CachingConfig.CACHE_RESOLVER_NAME, key = "{ #root.methodName, #author }")
    public ResponseEntity<List<Book>> getByAuthor(@PathVariable("author") String author){
        return ResponseEntity.ok(service.findByAuthor(author));
    }
}
