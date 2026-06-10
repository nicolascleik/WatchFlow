package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.*;
import com.watchflow.WatchFlow.core.usecase.amizade.impl.*;
import com.watchflow.WatchFlow.core.usecase.usuario.*;
import com.watchflow.WatchFlow.core.usecase.usuario.impl.*;
import com.watchflow.WatchFlow.core.usecase.catalogo.*;
import com.watchflow.WatchFlow.core.usecase.catalogo.impl.*;
import com.watchflow.WatchFlow.core.gateway.ChatGateway;
import com.watchflow.WatchFlow.core.usecase.chat.*;
import com.watchflow.WatchFlow.core.usecase.chat.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseInjectionConfig {

    // ==========================================
    // MÓDULO: USUÁRIO
    // ==========================================

    @Bean
    public CriarContaUseCase criarContaUseCase(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        return new CriarContaUseCaseImpl(usuarioGateway, codificadorSenhaGateway);
    }

    @Bean
    public FazerLoginUseCase fazerLoginUseCase(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        return new FazerLoginUseCaseImpl(usuarioGateway, codificadorSenhaGateway);
    }

    @Bean
    public BuscarUsuarioUseCase buscarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public AtualizarPerfilUseCase atualizarPerfilUseCase(UsuarioGateway usuarioGateway) {
        return new AtualizarPerfilUseCaseImpl(usuarioGateway);
    }

    @Bean
    public AtualizarPreferenciasUseCase atualizarPreferenciasUseCase(UsuarioGateway usuarioGateway, CatalogoGateway catalogoGateway) {
        return new AtualizarPreferenciasUseCaseImpl(usuarioGateway, catalogoGateway);
    }

    // ==========================================
    // MÓDULO: AMIZADE
    // ==========================================

    @Bean
    public EnviarConviteUseCase enviarConviteUseCase(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        return new EnviarConviteUseCaseImpl(amizadeGateway, usuarioGateway);
    }

    @Bean
    public ResponderConviteUseCase responderConviteUseCase(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        return new ResponderConviteUseCaseImpl(amizadeGateway, usuarioGateway);
    }

    @Bean
    public RemoverAmizadeUseCase removerAmizadeUseCase(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        return new RemoverAmizadeUseCaseImpl(amizadeGateway, usuarioGateway);
    }

    @Bean
    public ListarAmigosUseCase listarAmigosUseCase(UsuarioGateway usuarioGateway) {
        return new ListarAmigosUseCaseImpl(usuarioGateway);
    }

    @Bean
    public ListarConvitesUseCase listarConvitesUseCase(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        return new ListarConvitesUseCaseImpl(amizadeGateway, usuarioGateway);
    }

    // ==========================================
    // MÓDULO: CATÁLOGO / TMDB
    // ==========================================

    @Bean
    public BuscarMidiaUseCase buscarMidiaUseCase(CatalogoGateway catalogoGateway) {
        return new BuscarMidiaUseCaseImpl(catalogoGateway);
    }

    @Bean
    public DetalharMidiaUseCase detalharMidiaUseCase(CatalogoGateway catalogoGateway) {
        return new DetalharMidiaUseCaseImpl(catalogoGateway);
    }

    @Bean
    public RegistrarMidiaAssistidaUseCase registrarMidiaAssistidaUseCase(CatalogoGateway catalogoGateway, UsuarioGateway usuarioGateway) {
        return new RegistrarMidiaAssistidaUseCaseImpl(catalogoGateway, usuarioGateway);
    }

    @Bean
    public ListarCategoriasUseCase listarCategoriasUseCase(CatalogoGateway catalogoGateway) {
        return new ListarCategoriasUseCaseImpl(catalogoGateway);
    }

    // ==========================================
    // MÓDULO: CHAT
    // ==========================================

    @Bean
    public EnviarMensagemUseCase enviarMensagemUseCase(ChatGateway chatGateway, AmizadeGateway amizadeGateway) {
        return new EnviarMensagemUseCaseImpl(chatGateway, amizadeGateway);
    }

    @Bean
    public BuscarHistoricoUseCase buscarHistoricoUseCase(ChatGateway chatGateway) {
        return new BuscarHistoricoUseCaseImpl(chatGateway);
    }

    @Bean
    public MarcarMensagemLidaUseCase marcarMensagemLidaUseCase(ChatGateway chatGateway) {
        return new MarcarMensagemLidaUseCaseImpl(chatGateway);
    }
}