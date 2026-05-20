package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway.TmdbGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarFilmesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogoConfig {

    @Bean
    public BuscarFilmesUseCase buscarFilmesUseCase(TmdbGateway tmdbGateway) {
        return new BuscarFilmesUseCase(tmdbGateway);
    }
}