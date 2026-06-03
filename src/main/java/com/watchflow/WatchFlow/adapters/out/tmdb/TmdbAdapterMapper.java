package com.watchflow.WatchFlow.adapters.out.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbFilmeResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;

public class TmdbAdapterMapper {

    public static Filme toDomain(TmdbFilmeResponse response) {
        if (response == null) {
            return null;
        }

        Filme filme = new Filme();
        // tradução do formato do TMDB para o formato do nosso sistema
        filme.setTmdbId(response.id());
        filme.setTitulo(response.title());
        filme.setDescricao(response.overview());

        return filme;
    }
}
