package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import br.com.KevinVSantos.SimpleLibrary.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController extends AbstractController<String, BookService, Book> {
}
