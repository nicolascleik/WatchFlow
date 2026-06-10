package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbFilmeResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tmdb-client", url = "${tmdb.api.url}")
public interface TmdbFeignClient {

    // Usamos o /search/multi para o TMDB retornar tanto filmes quanto séries numa tacada só
    @GetMapping(value = "/search/multi", headers = "Authorization=Bearer ${tmdb.api.token}")
    TmdbPesquisaResponse pesquisarMulti(
            @RequestParam("query") String query,
            @RequestParam("language") String language,
            @RequestParam("page") int page
    );

    @GetMapping(value = "/movie/{id}", headers = "Authorization=Bearer ${tmdb.api.token}")
    TmdbFilmeResponse buscarDetalhesFilme(
            @PathVariable("id") Long id,
            @RequestParam("language") String language
    );

    @GetMapping(value = "/tv/{id}", headers = "Authorization=Bearer ${tmdb.api.token}")
    TmdbFilmeResponse buscarDetalhesSerie(
            @PathVariable("id") Long id,
            @RequestParam("language") String language
    );
}