package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.domain.midia.TipoMidia;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaCommand;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegistrarMidiaRequest(
        @NotNull(message = "O ID do TMDB é obrigatório")
        Long tmdbId,
        
        @NotNull(message = "O tipo de mídia (FILME ou SERIE) é obrigatório")
        TipoMidia tipo
) {
    public RegistrarMidiaAssistidaCommand toCommand(UUID usuarioLogadoId) {
        return new RegistrarMidiaAssistidaCommand(usuarioLogadoId, tmdbId, tipo);
    }
}