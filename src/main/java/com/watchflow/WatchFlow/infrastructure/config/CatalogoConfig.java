package com.watchflow.watchflow.infrastructure.config;

import com.watchflow.watchflow.adapters.out.gateway.TmdbGateway;
import com.watchflow.watchflow.core.usecase.catalogo.BuscarFilmesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogoConfig {

    @Bean
    public BuscarFilmesUseCase buscarFilmesUseCase(TmdbGateway tmdbGateway) {
        return new BuscarFilmesUseCase(tmdbGateway);
    }
}