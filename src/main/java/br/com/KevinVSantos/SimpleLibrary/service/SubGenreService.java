package br.com.KevinVSantos.SimpleLibrary.service;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.SubGenre;
import br.com.KevinVSantos.SimpleLibrary.repository.ISubGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class SubGenreService extends AbstractService<String, SubGenre, ISubGenreRepository> {
}
