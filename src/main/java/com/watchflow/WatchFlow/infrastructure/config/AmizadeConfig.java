package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.SolicitarAmizadeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmizadeConfig {

    @Bean
    public SolicitarAmizadeUseCase solicitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        return new SolicitarAmizadeUseCase(amizadeGateway);
    }
}