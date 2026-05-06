package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;
import com.watchflow.WatchFlow.core.usecase.usuario.impl.CriarContaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public CriarContaUseCase criarContaUseCase(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        return new CriarContaUseCaseImpl(usuarioGateway, codificadorSenhaGateway);
    }
}