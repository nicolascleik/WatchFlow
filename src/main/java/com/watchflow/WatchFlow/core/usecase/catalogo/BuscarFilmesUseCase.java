package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;
import com.watchflow.WatchFlow.core.domain.midia.Filme;

import java.util.List;

public class BuscarFilmesUseCase {

    private final TmdbGateway tmdbGateway;

    public BuscarFilmesUseCase(TmdbGateway tmdbGateway) {
        this.tmdbGateway = tmdbGateway;
    }

    public List<Filme> executarBuscaPorTitulo(String titulo) {
        return tmdbGateway.buscarFilmePorTitulo(titulo);
    }
}