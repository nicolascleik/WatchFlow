package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.TmdbAdapterMapper;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbMediaResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbProvidersResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TmdbClientImpl implements TmdbGateway {

    private final TmdbFeignClient feignClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbClientImpl(TmdbFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    // MÉTODOS DE FILME

    @Override
    @Cacheable(value = "buscaFilmes", key = "#titulo + '-' + #idioma + '-' + #pagina")
    public List<Filme> buscarFilmes(String titulo, String idioma, int pagina) {
        TmdbPesquisaResponse resposta = feignClient.buscarFilmes(apiKey, titulo, idioma, pagina);
        if (resposta == null || resposta.results() == null) return List.of();
        return resposta.results().stream().map(TmdbAdapterMapper::toFilme).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "filmesPopulares", key = "#idioma + '-' + #pagina")
    public List<Filme> buscarFilmesPopulares(String idioma, int pagina) {
        TmdbPesquisaResponse resposta = feignClient.buscarFilmesPopulares(apiKey, idioma, pagina);
        if (resposta == null || resposta.results() == null) return List.of();
        return resposta.results().stream().map(TmdbAdapterMapper::toFilme).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "detalhesFilme", key = "#id + '-' + #idioma")
    public Filme buscarDetalhesFilme(Long id, String idioma) {
        TmdbMediaResponse response = feignClient.buscarDetalhesFilme(id, apiKey, idioma);
        Filme filme = TmdbAdapterMapper.toFilme(response);

        if (filme != null) {
            TmdbProvidersResponse providers = feignClient.buscarProvedoresFilme(id, apiKey);
            filme.setPlataformasDisponiveis(extrairPlataformasNoBrasil(providers));
        }
        return filme;
    }

    @Override
    @Cacheable(value = "descobrirFilmes", key = "#genero + '-' + #ano + '-' + #idioma + '-' + #pagina")
    public List<Filme> descobrirFilmes(String genero, Integer ano, String idioma, int pagina) {
        TmdbPesquisaResponse resposta = feignClient.descobrirFilmes(apiKey, idioma, genero, ano, pagina);
        if (resposta == null || resposta.results() == null) return List.of();
        return resposta.results().stream().map(TmdbAdapterMapper::toFilme).collect(Collectors.toList());
    }

    // MÉTODOS DE SÉRIE

    @Override
    @Cacheable(value = "buscaSeries", key = "#titulo + '-' + #idioma + '-' + #pagina")
    public List<Serie> buscarSeries(String titulo, String idioma, int pagina) {
        TmdbPesquisaResponse resposta = feignClient.buscarSeries(apiKey, titulo, idioma, pagina);
        if (resposta == null || resposta.results() == null) return List.of();
        return resposta.results().stream().map(TmdbAdapterMapper::toSerie).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "seriesPopulares", key = "#idioma + '-' + #pagina")
    public List<Serie> buscarSeriesPopulares(String idioma, int pagina) {
        TmdbPesquisaResponse resposta = feignClient.buscarSeriesPopulares(apiKey, idioma, pagina);
        if (resposta == null || resposta.results() == null) return List.of();
        return resposta.results().stream().map(TmdbAdapterMapper::toSerie).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "detalhesSerie", key = "#id + '-' + #idioma")
    public Serie buscarDetalhesSerie(Long id, String idioma) {
        TmdbMediaResponse response = feignClient.buscarDetalhesSerie(id, apiKey, idioma);
        Serie serie = TmdbAdapterMapper.toSerie(response);

        if (serie != null) {
            TmdbProvidersResponse providers = feignClient.buscarProvedoresSerie(id, apiKey);
            serie.setPlataformasDisponiveis(extrairPlataformasNoBrasil(providers));
        }
        return serie;
    }

    // UTILITÁRIOS

    private List<String> extrairPlataformasNoBrasil(TmdbProvidersResponse response) {
        List<String> plataformas = new ArrayList<>();
        if (response != null && response.results() != null && response.results().containsKey("BR")) {
            var brasil = response.results().get("BR");
            if (brasil.flatrate() != null) {
                brasil.flatrate().forEach(p -> plataformas.add(p.providerName()));
            }
        }
        return plataformas;
    }
}