package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.core.gateway.TmdbGateway;

public class DetalharMidiaUseCase {

    private final TmdbGateway tmdbGateway;

    public DetalharMidiaUseCase(TmdbGateway tmdbGateway) {
        this.tmdbGateway = tmdbGateway;
    }

    public Filme detalharFilme(Long id, String idioma) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do filme não pode ser nulo.");
        }
        return tmdbGateway.buscarDetalhesFilme(id, idioma);
    }

    public Serie detalharSerie(Long id, String idioma) {
        if (id == null) {
            throw new IllegalArgumentException("O ID da série não pode ser nulo.");
        }
        return tmdbGateway.buscarDetalhesSerie(id, idioma);
    }
}