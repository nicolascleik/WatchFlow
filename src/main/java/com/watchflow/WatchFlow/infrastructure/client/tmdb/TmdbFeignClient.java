package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// o spring vai ler a URL lá no application.properties
@FeignClient(name = "tmdb", url = "${tmdb.api.url}")
public interface TmdbFeignClient {

    // endereço exato dentro do site do TMDB que busca filmes
    @GetMapping("/search/movie")
    TmdbPesquisaResponse buscarFilmesNaInternet(
            @RequestParam("api_key") String apiKey,
            @RequestParam("query") String tituloDoFilme,
            @RequestParam("language") String idioma
    );
}