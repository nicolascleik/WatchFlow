package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPerfilCommand;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AtualizarPerfilRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String estado
) {
    public AtualizarPerfilCommand toCommand(UUID usuarioLogadoId) {
        return new AtualizarPerfilCommand(usuarioLogadoId, nome, cidade, estado);
    }
}