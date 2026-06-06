package com.watchflow.watchflow.adapters.in.controller.catalog;

import com.watchflow.watchflow.core.domain.midia.Filme;
import com.watchflow.watchflow.core.usecase.catalogo.BuscarFilmesUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    private final BuscarFilmesUseCase buscarFilmesUseCase;

    public CatalogController(BuscarFilmesUseCase buscarFilmesUseCase) {
        this.buscarFilmesUseCase = buscarFilmesUseCase;
    }

    @GetMapping("/movies/search")
    public List<Filme> buscarPorTitulo(@RequestParam String title) {
        return buscarFilmesUseCase.executarBuscaPorTitulo(title);
    }
}