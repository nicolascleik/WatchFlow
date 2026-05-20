package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.SolicitarAmizadeUseCase;
// import com.watchflow.WatchFlow.core.usecase.amizade.impl.SolicitarAmizadeUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmizadeConfig {

    @Bean
    public SolicitarAmizadeUseCase solicitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        SolicitarAmizadeUseCase solicitarAmizadeUseCase = new SolicitarAmizadeUseCase(amizadeGateway);

        return solicitarAmizadeUseCase;
        // return new SolicitarAmizadeUseCaseImpl(amizadeGateway); 
    }
}