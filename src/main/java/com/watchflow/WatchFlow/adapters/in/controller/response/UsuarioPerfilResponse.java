package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.UUID;

public record UsuarioPerfilResponse(
        UUID id,
        String nome,
        String email,
        String cidade,
        String estado
) {
    public static UsuarioPerfilResponse from(Usuario usuario) {
        return new UsuarioPerfilResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCidade(),
                usuario.getEstado()
        );
    }
}