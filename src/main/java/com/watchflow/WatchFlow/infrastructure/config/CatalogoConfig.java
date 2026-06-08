package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.gateway.TmdbGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.DetalharMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.ListarCategoriasUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.impl.ListarCategoriasUseCaseImpl;
import com.watchflow.WatchFlow.core.usecase.catalogo.impl.RegistrarMidiaAssistidaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogoConfig {

    private final CatalogoGateway catalogoGateway;
    private final UsuarioGateway usuarioGateway;

    public CatalogoConfig(CatalogoGateway catalogoGateway, UsuarioGateway usuarioGateway) {
        this.catalogoGateway = catalogoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Bean
    public ListarCategoriasUseCase listarCategoriasUseCase() {
        return new ListarCategoriasUseCaseImpl(catalogoGateway);
    }

    @Bean
    public RegistrarMidiaAssistidaUseCase registrarMidiaAssistidaUseCase() {
        return new RegistrarMidiaAssistidaUseCaseImpl(catalogoGateway, usuarioGateway);
    }

    @Bean
    public BuscarMidiaUseCase buscarMidiaUseCase(TmdbGateway tmdbGateway) {
        return new BuscarMidiaUseCase(tmdbGateway);
    }

    @Bean
    public DetalharMidiaUseCase detalharMidiaUseCase(TmdbGateway tmdbGateway) {
        return new DetalharMidiaUseCase(tmdbGateway);
    }
}