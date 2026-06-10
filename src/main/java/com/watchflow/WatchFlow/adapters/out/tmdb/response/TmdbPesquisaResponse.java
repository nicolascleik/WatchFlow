package com.watchflow.WatchFlow.adapters.out.tmdb.response;

import java.util.List;

public record TmdbPesquisaResponse(
        Integer page,
        List<TmdbFilmeResponse> results,
        Integer total_pages,
        Integer total_results
) {}