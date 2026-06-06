package com.watchflow.watchflow.infrastructure.config;

import com.watchflow.watchflow.core.gateway.AmizadeGateway;
import com.watchflow.watchflow.core.usecase.amizade.SolicitarAmizadeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmizadeConfig {

    @Bean
    public SolicitarAmizadeUseCase solicitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        return new SolicitarAmizadeUseCase(amizadeGateway);
    }
}