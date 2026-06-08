package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.TipoMidia;
import java.util.UUID;

public record RegistrarMidiaAssistidaCommand(
        UUID usuarioId,
        Long tmdbId,
        TipoMidia tipoMidia
) {}