package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbProviderItem(
        @JsonProperty("provider_name") String providerName
) {}