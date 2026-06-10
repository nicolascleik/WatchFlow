package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPerfilCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPerfilUseCase;

public class AtualizarPerfilUseCaseImpl implements AtualizarPerfilUseCase {

    private final UsuarioGateway usuarioGateway;

    public AtualizarPerfilUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(AtualizarPerfilCommand command) {
        Usuario usuario = usuarioGateway.buscarPorId(command.usuarioId());

        if (usuario == null) {
            throw new NaoEncontradoException("Usuário não encontrado.");
        }

        usuario.atualizarPerfil(command.nome(), command.cidade(), command.estado());

        usuarioGateway.salvar(usuario);
    }
}