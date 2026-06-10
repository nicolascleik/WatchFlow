package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.usecase.amizade.ResponderConviteCommand;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ResponderConviteRequest(
        @NotNull(message = "A decisão (aceitar) é obrigatória")
        Boolean aceitar
) {
    public ResponderConviteCommand toCommand(UUID amizadeId, UUID usuarioLogadoId) {
        return new ResponderConviteCommand(amizadeId, usuarioLogadoId, aceitar);
    }
}