package com.watchflow.WatchFlow.adapters.out.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbFilmeResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.domain.midia.Serie;

import java.util.Collections;

public class TmdbAdapterMapper {

    public static MidiaBase toDomain(TmdbFilmeResponse response) {
        if (response == null) return null;

        // Regra de dedução: TMDB usa 'name' e 'firstAirDate' para Séries.
        boolean isSerie = response.name() != null && !response.name().isEmpty();

        String tituloFinal = isSerie ? response.name() : response.title();
        String dataLancamentoRaw = isSerie ? response.firstAirDate() : response.releaseDate();
        
        Integer anoLancamento = extrairAno(dataLancamentoRaw);

        if (isSerie) {
            return Serie.criar(
                    response.id(),
                    tituloFinal,
                    response.overview(),
                    anoLancamento,
                    response.voteAverage(),
                    Collections.emptyList(), // Plataformas vêm de outro endpoint do TMDB (provedores)
                    response.numberOfSeasons() != null ? response.numberOfSeasons() : 1
            );
        } else {
            return Filme.criar(
                    response.id(),
                    tituloFinal,
                    response.overview(),
                    anoLancamento,
                    response.voteAverage(),
                    Collections.emptyList(),
                    response.runtime() != null ? response.runtime() : 0
            );
        }
    }

    private static Integer extrairAno(String dataRaw) {
        if (dataRaw == null || dataRaw.length() < 4) {
            return null;
        }
        try {
            // O TMDB sempre retorna no formato "YYYY-MM-DD"
            return Integer.parseInt(dataRaw.substring(0, 4));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}