package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public record TmdbProvidersResponse(
        @JsonProperty("results") Map<String, Regiao> results
) {
    public record Regiao(
            @JsonProperty("flatrate") List<TmdbProviderItem> flatrate, // assinatura (Netflix, HBO)
            @JsonProperty("rent") List<TmdbProviderItem> rent // aluguel
    ) {}
}