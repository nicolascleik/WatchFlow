package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.AceitarAmizadeUseCase;
import com.watchflow.WatchFlow.core.usecase.amizade.BuscarListaAmigosUseCase;
import com.watchflow.WatchFlow.core.usecase.amizade.SolicitarAmizadeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmizadeConfig {

    @Bean
    public SolicitarAmizadeUseCase solicitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        return new SolicitarAmizadeUseCase(amizadeGateway);
    }

    @Bean
    public BuscarListaAmigosUseCase buscarListaAmigosUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarListaAmigosUseCase(usuarioGateway);
    }

    @Bean
    public AceitarAmizadeUseCase aceitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        return new AceitarAmizadeUseCase(amizadeGateway);
    }
}