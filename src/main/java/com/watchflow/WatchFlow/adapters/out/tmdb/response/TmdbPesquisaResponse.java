package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbPesquisaResponse(
        @JsonProperty("results") List<TmdbFilmeResponse> results
) {
}