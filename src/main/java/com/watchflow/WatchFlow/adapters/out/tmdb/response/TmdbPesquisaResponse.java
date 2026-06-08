package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// lista de resultados genérica usada na busca de Filmes e Séries
public record TmdbPesquisaResponse(
        @JsonProperty("results") List<TmdbMediaResponse> results
) {
}