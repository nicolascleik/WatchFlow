package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.BuscarUsuarioUseCase;

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
            throw new NaoEncontradoException("Usuário não encontrado no sistema.");
        }
        
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioGateway.listarTodos();
    }
}