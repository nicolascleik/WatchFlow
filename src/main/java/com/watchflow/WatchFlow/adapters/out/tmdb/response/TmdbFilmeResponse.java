package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbFilmeResponse(
        Long id,
        String title,
        String name, // TMDB usa 'name' para séries em vez de 'title'
        String overview,
        
        @JsonProperty("release_date") 
        String releaseDate,
        
        @JsonProperty("first_air_date") 
        String firstAirDate,
        
        @JsonProperty("vote_average") 
        Double voteAverage,
        
        Integer runtime, // Apenas filmes
        
        @JsonProperty("number_of_seasons") 
        Integer numberOfSeasons // Apenas séries
) {}