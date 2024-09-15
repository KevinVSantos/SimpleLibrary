package br.com.KevinVSantos.SimpleLibrary.service;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.SubGenre;
import br.com.KevinVSantos.SimpleLibrary.handler.exception.EntityNotFoundException;
import br.com.KevinVSantos.SimpleLibrary.repository.ISubGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubGenreService extends AbstractService<String, SubGenre, ISubGenreRepository> {

    @Autowired private GenreService genreService;


    @Override
    public SubGenre create(SubGenre entity){
        validMainGenre(entity.getMainGenreTitle());
        return super.create(entity);
    }

    @Override
    public SubGenre update(SubGenre entity){
        validMainGenre(entity.getMainGenreTitle());
        return super.update(entity);
    }

    private void validMainGenre(String mainGenreTitle){
        try {
            genreService.findById(mainGenreTitle);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Main genre not found");
        }
    }
}
