package com.watchflow.watchflow.adapters.out.gateway;

import com.watchflow.watchflow.core.domain.midia.Filme;
import java.util.List;

public interface TmdbGateway {
    List<Filme> buscarFilmePorTitulo(String titulo);
}
