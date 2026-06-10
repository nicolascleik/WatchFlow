package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.UUID;

public record AmigoResponse(
        UUID id,
        String nome,
        String cidade,
        String estado
) {
    public static AmigoResponse from(Usuario usuario) {
        return new AmigoResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCidade(),
                usuario.getEstado()
        );
    }
}