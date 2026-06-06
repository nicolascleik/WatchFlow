package com.watchflow.watchflow.core.usecase.usuario.impl;

import com.watchflow.watchflow.core.domain.usuario.Usuario;
import com.watchflow.watchflow.core.gateway.UsuarioGateway;
import com.watchflow.watchflow.core.usecase.usuario.BuscarUsuarioUseCase;
import com.watchflow.watchflow.core.usecase.usuario.UsuarioNaoEncontradoException;

import java.util.List;
import java.util.UUID;

public class BuscarUsuarioUseCaseImpl implements BuscarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public BuscarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario executar(UUID id) {
        Usuario usuario = usuarioGateway.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado para o id: " + id);
        }

        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioGateway.buscarTodos();
    }
}
