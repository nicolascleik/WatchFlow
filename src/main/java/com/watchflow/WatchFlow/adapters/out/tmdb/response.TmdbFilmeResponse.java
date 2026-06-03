package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbFilmeResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("overview") String overview,
        @JsonProperty("release_date") String releaseDate
) {
}
