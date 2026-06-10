package com.watchflow.WatchFlow.adapters.out.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbMidiaResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;

public class TmdbAdapterMapper {

    public static Filme toFilme(TmdbMidiaResponse response) {
        if (response == null) return null;

        Filme filme = new Filme();
        filme.setTmdbId(response.id());
        filme.setTitulo(response.title() != null ? response.title() : response.name());
        filme.setDescricao(response.overview());

        filme.setNota(response.voteAverage());
        filme.setPosterPath(response.posterPath());

        if (response.releaseDate() != null && response.releaseDate().length() >= 4) {
            filme.setAnoDeLancamento(Integer.parseInt(response.releaseDate().substring(0, 4)));
        }

        return filme;
    }

    public static Serie toSerie(TmdbMidiaResponse response) {
        if (response == null) return null;

        Serie serie = new Serie();
        serie.setTmdbId(response.id());
        serie.setTitulo(response.name() != null ? response.name() : response.title());
        serie.setDescricao(response.overview());

        serie.setNota(response.voteAverage());
        serie.setPosterPath(response.posterPath());

        if (response.firstAirDate() != null && response.firstAirDate().length() >= 4) {
            serie.setAnoDeLancamento(Integer.parseInt(response.firstAirDate().substring(0, 4)));
        }

        return serie;
    }
}