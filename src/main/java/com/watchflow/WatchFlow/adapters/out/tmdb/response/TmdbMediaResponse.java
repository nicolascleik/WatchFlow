package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

// DTO unificado: o TMDB manda 'title' para filmes e 'name' para séries
// mapeamos ambos para não perder dados
public record TmdbMediaResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("name") String name,
        @JsonProperty("overview") String overview,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("first_air_date") String firstAirDate
) {
}