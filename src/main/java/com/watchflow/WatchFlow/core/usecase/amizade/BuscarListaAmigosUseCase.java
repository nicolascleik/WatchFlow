package com.watchflow.WatchFlow.core.usecase.amizade;

import java.util.List;
import java.util.UUID;

import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarListaAmigosUseCase {

    private final UsuarioGateway usuarioGateway;

    public List<Usuario> executar(UUID usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }

        // 1. Busca o usuário dono da lista
        Usuario usuario = usuarioGateway.buscarPorId(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        // 2. Verifica se o usuário tem amigos
        if (usuario.getAmigosIds() == null || usuario.getAmigosIds().isEmpty()) {
            return List.of(); // Retorna lista vazia caso não tenha amigos
        }

        // 3. Busca e retorna os perfis completos dos amigos usando os IDs
        return usuarioGateway.buscarUsuariosPorIds(usuario.getAmigosIds());
    }
}