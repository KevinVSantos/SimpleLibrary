package br.com.KevinVSantos.SimpleLibrary.service;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import br.com.KevinVSantos.SimpleLibrary.repository.IBookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Long, Book, IBookRepository> {
}
