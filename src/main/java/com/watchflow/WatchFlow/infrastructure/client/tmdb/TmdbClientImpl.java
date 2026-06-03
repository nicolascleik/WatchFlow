package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.tmdb.TmdbAdapterMapper;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TmdbClientImpl implements TmdbGateway {

    private final TmdbFeignClient feignClient;

    // puxa a chave do application.properties
    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbClientImpl(TmdbFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public List<Filme> buscarFilmePorTitulo(String titulo) {

        // faz a requisição real para a internet usando o feign
        TmdbPesquisaResponse respostaTmdb = feignClient.buscarFilmesNaInternet(apiKey, titulo, "pt-BR");

        // verifica se a API do TMDB não retornou nada
        if (respostaTmdb == null || respostaTmdb.results() == null) {
            return List.of(); // devolve uma lista vazia
        }

        // traduz a lista suja do TMDB para a lista limpa do nosso domínio (filme)
        return respostaTmdb.results().stream()
                .map(TmdbAdapterMapper::toDomain)
                .collect(Collectors.toList());
    }
}