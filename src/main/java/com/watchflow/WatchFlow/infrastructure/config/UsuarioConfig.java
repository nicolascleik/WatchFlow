package com.watchflow.watchflow.infrastructure.config;

import com.watchflow.watchflow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.watchflow.core.gateway.UsuarioGateway;
import com.watchflow.watchflow.core.usecase.usuario.BuscarUsuarioUseCase;
import com.watchflow.watchflow.core.usecase.usuario.CriarContaUseCase;
import com.watchflow.watchflow.core.usecase.usuario.impl.BuscarUsuarioUseCaseImpl;
import com.watchflow.watchflow.core.usecase.usuario.impl.CriarContaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public CriarContaUseCase criarContaUseCase(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        return new CriarContaUseCaseImpl(usuarioGateway, codificadorSenhaGateway);
    }

    @Bean
    public BuscarUsuarioUseCase buscarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioUseCaseImpl(usuarioGateway);
    }
}