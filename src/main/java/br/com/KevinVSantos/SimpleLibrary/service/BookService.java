package br.com.KevinVSantos.SimpleLibrary.service;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Book;
import br.com.KevinVSantos.SimpleLibrary.handler.exception.EntityNotFoundException;
import br.com.KevinVSantos.SimpleLibrary.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<String, Book, IBookRepository> {

    @Autowired private GenreService genreService;
    @Autowired private SubGenreService subGenreService;

    @Override
    public Book create(Book entity){
        validMainGenre(entity.getMainGenreTitle());
        validSubGenre(entity.getSubGenreTitle());
        return super.create(entity);
    }

    @Override
    public Book update(Book entity){
        validMainGenre(entity.getMainGenreTitle());
        validSubGenre(entity.getSubGenreTitle());
        return super.update(entity);
    }

    private void validMainGenre(String mainGenreTitle){
        try {
            genreService.findById(mainGenreTitle);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Main genre not found");
        }
    }

    private void validSubGenre(String mainSubTitle){
        try {
            subGenreService.findById(mainSubTitle);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Sub genre not found");
        }
    }

}
