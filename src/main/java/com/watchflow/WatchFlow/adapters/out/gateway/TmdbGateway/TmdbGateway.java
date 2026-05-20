package com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import java.util.List;

public interface TmdbGateway {
    List<Filme> buscarFilmePorTitulo(String titulo);
}