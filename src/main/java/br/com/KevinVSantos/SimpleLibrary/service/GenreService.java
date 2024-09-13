package br.com.KevinVSantos.SimpleLibrary.service;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Genre;
import br.com.KevinVSantos.SimpleLibrary.repository.IGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends AbstractService<String, Genre, IGenreRepository> {
}
