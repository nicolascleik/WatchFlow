package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.TipoMidia;

import java.util.UUID;

public record RegistrarMidiaAssistidaCommand(
        UUID usuarioLogadoId,
        Long tmdbId,
        TipoMidia tipo
) {
}