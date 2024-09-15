package br.com.KevinVSantos.SimpleLibrary.controller;

import br.com.KevinVSantos.SimpleLibrary.domain.entity.Genre;
import br.com.KevinVSantos.SimpleLibrary.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/genre")
public class GenreController extends AbstractController<String, GenreService, Genre> {
}
