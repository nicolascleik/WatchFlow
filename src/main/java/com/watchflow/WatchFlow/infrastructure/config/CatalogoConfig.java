package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.CatalogoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogoConfig {

    // instancia o UseCase garantindo que as dependências do Core sejam resolvidas fora das classes de negócio
    @Bean
    public CatalogoUseCase catalogoUseCase(TmdbGateway tmdbGateway) {
        return new CatalogoUseCase(tmdbGateway);
    }
}