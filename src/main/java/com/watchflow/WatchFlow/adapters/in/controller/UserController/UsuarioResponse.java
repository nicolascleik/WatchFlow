package com.watchflow.WatchFlow.adapters.in.controller.UserController;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UsuarioResponse {
    private UUID id;
    private String nome;
    private String email;
    private String cidade;
    private String estado;

    public static UsuarioResponse from(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cidade(usuario.getCidade())
                .estado(usuario.getEstado())
                .build();
    }
}
