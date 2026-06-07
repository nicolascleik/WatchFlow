package com.watchflow.WatchFlow.adapters.out.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbMediaResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;

public class TmdbAdapterMapper {

    // converte o JSON sujo do TMDB para a nossa entidade limpa de Filme
    public static Filme toFilme(TmdbMediaResponse response) {
        if (response == null) return null;

        Filme filme = new Filme();
        filme.setTmdbId(response.id());
        filme.setTitulo(response.title() != null ? response.title() : response.name());
        filme.setDescricao(response.overview());

        // pega o ano da data no formato YYYY-MM-DD
        if (response.releaseDate() != null && response.releaseDate().length() >= 4) {
            filme.setAnoDeLancamento(Integer.parseInt(response.releaseDate().substring(0, 4)));
        }

        return filme;
    }

    // converte o JSON sujo do TMDB para a nossa entidade limpa de Série
    public static Serie toSerie(TmdbMediaResponse response) {
        if (response == null) return null;

        Serie serie = new Serie();
        serie.setTmdbId(response.id());
        serie.setTitulo(response.name() != null ? response.name() : response.title());
        serie.setDescricao(response.overview());

        if (response.firstAirDate() != null && response.firstAirDate().length() >= 4) {
            serie.setAnoDeLancamento(Integer.parseInt(response.firstAirDate().substring(0, 4)));
        }

        return serie;
    }
}