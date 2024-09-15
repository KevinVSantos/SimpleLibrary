package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.SubGenre;
import br.com.KevinVSantos.SimpleLibrary.service.SubGenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subgenre")
public class SubGenreController extends AbstractController<String, SubGenreService, SubGenre> {
}
