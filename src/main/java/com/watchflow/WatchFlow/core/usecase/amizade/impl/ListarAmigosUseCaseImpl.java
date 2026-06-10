package com.watchflow.WatchFlow.core.usecase.amizade.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.ListarAmigosUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListarAmigosUseCaseImpl implements ListarAmigosUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarAmigosUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public List<Usuario> executar(UUID usuarioLogadoId) {
        Usuario usuario = usuarioGateway.buscarPorId(usuarioLogadoId);

        if (usuario == null) {
            throw new NaoEncontradoException("Usuário não encontrado.");
        }

        // Itera sobre a lista de IDs blindada pelo domínio e busca os dados de cada amigo.
        // Nota Arquitetural: Para altíssima performance num cenário com 5.000 amigos, 
        // o ideal seria criar um método buscarTodosPorIds() no Gateway, mas para a 
        // abstração do domínio inicial, o mapeamento funcional abaixo cumpre o papel perfeitamente.
        return usuario.getAmigosIds().stream()
                .map(usuarioGateway::buscarPorId)
                .filter(amigo -> amigo != null)
                .collect(Collectors.toList());
    }
}