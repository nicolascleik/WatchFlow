package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.core.gateway.TmdbGateway;
import java.util.List;

public class BuscarMidiaUseCase {

    private final TmdbGateway tmdbGateway;

    public BuscarMidiaUseCase(TmdbGateway tmdbGateway) {
        this.tmdbGateway = tmdbGateway;
    }

    public List<Filme> buscarFilmes(String titulo, String idioma, int pagina) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio.");
        }
        return tmdbGateway.buscarFilmes(titulo.trim(), idioma, pagina);
    }

    public List<Filme> buscarFilmesPopulares(String idioma, int pagina) {
        return tmdbGateway.buscarFilmesPopulares(idioma, pagina);
    }

    public List<Filme> descobrirFilmes(String genero, Integer ano, String idioma, int pagina) {
        return tmdbGateway.descobrirFilmes(genero, ano, idioma, pagina);
    }

    public List<Serie> buscarSeries(String titulo, String idioma, int pagina) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio.");
        }
        return tmdbGateway.buscarSeries(titulo.trim(), idioma, pagina);
    }

    public List<Serie> buscarSeriesPopulares(String idioma, int pagina) {
        return tmdbGateway.buscarSeriesPopulares(idioma, pagina);
    }
}