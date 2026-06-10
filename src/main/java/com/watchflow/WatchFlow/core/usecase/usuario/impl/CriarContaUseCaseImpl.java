package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;

public class CriarContaUseCaseImpl implements CriarContaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final CodificadorSenhaGateway codificadorSenhaGateway;

    // Sem anotações do Spring! A injeção será configurada manualmente na camada de Infra.
    public CriarContaUseCaseImpl(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        this.usuarioGateway = usuarioGateway;
        this.codificadorSenhaGateway = codificadorSenhaGateway;
    }

    @Override
    public void executar(CriarContaCommand command) {
        if (usuarioGateway.existePorEmail(command.email())) {
            throw new RegraNegocioException("Este e-mail já está em uso.");
        }

        String senhaHasheada = codificadorSenhaGateway.codificar(command.senhaBruta());

        Usuario novoUsuario = Usuario.criar(
                command.nome(),
                command.email(),
                senhaHasheada,
                command.cidade(),
                command.estado()
        );

        usuarioGateway.salvar(novoUsuario);
    }
}