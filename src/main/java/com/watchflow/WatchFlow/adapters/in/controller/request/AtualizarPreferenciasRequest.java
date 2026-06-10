package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPreferenciasCommand;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AtualizarPreferenciasRequest(
        @NotEmpty(message = "A lista de categorias não pode estar vazia")
        Set<UUID> categoriasIds
) {
    public AtualizarPreferenciasCommand toCommand(UUID usuarioLogadoId) {
        return new AtualizarPreferenciasCommand(usuarioLogadoId, categoriasIds);
    }
}