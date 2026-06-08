package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbMidiaResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbProvidersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tmdb", url = "${tmdb.api.url}")
public interface TmdbFeignClient {

    // ROTAS DE FILMES
    @GetMapping("/search/movie")
    TmdbPesquisaResponse buscarFilmes(@RequestParam("api_key") String apiKey, @RequestParam("query") String titulo, @RequestParam("language") String idioma, @RequestParam("page") int pagina);

    @GetMapping("/movie/popular")
    TmdbPesquisaResponse buscarFilmesPopulares(@RequestParam("api_key") String apiKey, @RequestParam("language") String idioma, @RequestParam("page") int pagina);

    @GetMapping("/movie/{movie_id}")
    TmdbMidiaResponse buscarDetalhesFilme(@PathVariable("movie_id") Long id, @RequestParam("api_key") String apiKey, @RequestParam("language") String idioma);

    @GetMapping("/movie/{movie_id}/watch/providers")
    TmdbProvidersResponse buscarProvedoresFilme(@PathVariable("movie_id") Long id, @RequestParam("api_key") String apiKey);

    @GetMapping("/discover/movie")
    TmdbPesquisaResponse descobrirFilmes(@RequestParam("api_key") String apiKey, @RequestParam("language") String idioma, @RequestParam(value = "with_genres", required = false) String genero, @RequestParam(value = "primary_release_year", required = false) Integer ano, @RequestParam("page") int pagina);

    // ROTAS DE SÉRIES E ANIMES (TV)
    @GetMapping("/search/tv")
    TmdbPesquisaResponse buscarSeries(@RequestParam("api_key") String apiKey, @RequestParam("query") String titulo, @RequestParam("language") String idioma, @RequestParam("page") int pagina);

    @GetMapping("/tv/popular")
    TmdbPesquisaResponse buscarSeriesPopulares(@RequestParam("api_key") String apiKey, @RequestParam("language") String idioma, @RequestParam("page") int pagina);

    @GetMapping("/tv/{tv_id}")
    TmdbMidiaResponse buscarDetalhesSerie(@PathVariable("tv_id") Long id, @RequestParam("api_key") String apiKey, @RequestParam("language") String idioma);

    @GetMapping("/tv/{tv_id}/watch/providers")
    TmdbProvidersResponse buscarProvedoresSerie(@PathVariable("tv_id") Long id, @RequestParam("api_key") String apiKey);
}