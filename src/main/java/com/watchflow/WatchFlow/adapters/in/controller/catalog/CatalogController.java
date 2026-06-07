package com.watchflow.WatchFlow.adapters.in.controller.catalog;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.core.usecase.catalogo.CatalogoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogoUseCase catalogoUseCase;

    public CatalogController(CatalogoUseCase catalogoUseCase) {
        this.catalogoUseCase = catalogoUseCase;
    }

    // FILMES

    @GetMapping("/movies/search")
    public ResponseEntity<List<Filme>> buscarFilmes(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(catalogoUseCase.buscarFilmes(title, idioma, page));
    }

    @GetMapping("/movies/popular")
    public ResponseEntity<List<Filme>> filmesPopulares(
            @RequestParam(defaultValue = "1") int page,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(catalogoUseCase.buscarFilmesPopulares(idioma, page));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Filme> detalhesFilme(
            @PathVariable Long id,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        Filme filme = catalogoUseCase.buscarDetalhesFilme(id, idioma);
        return filme != null ? ResponseEntity.ok(filme) : ResponseEntity.notFound().build();
    }

    @GetMapping("/movies/discover")
    public ResponseEntity<List<Filme>> descobrirFilmes(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "1") int page,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(catalogoUseCase.descobrirFilmes(genre, year, idioma, page));
    }

    // SÉRIES E ANIMES

    @GetMapping("/tv/search")
    public ResponseEntity<List<Serie>> buscarSeries(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(catalogoUseCase.buscarSeries(title, idioma, page));
    }

    @GetMapping("/tv/popular")
    public ResponseEntity<List<Serie>> seriesPopulares(
            @RequestParam(defaultValue = "1") int page,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(catalogoUseCase.buscarSeriesPopulares(idioma, page));
    }

    @GetMapping("/tv/{id}")
    public ResponseEntity<Serie> detalhesSerie(
            @PathVariable Long id,
            @RequestHeader(value = "Accept-Language", defaultValue = "pt-BR") String idioma) {
        Serie serie = catalogoUseCase.buscarDetalhesSerie(id, idioma);
        return serie != null ? ResponseEntity.ok(serie) : ResponseEntity.notFound().build();
    }
}