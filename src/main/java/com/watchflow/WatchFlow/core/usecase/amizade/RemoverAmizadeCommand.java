package com.watchflow.WatchFlow.core.usecase.amizade;

import java.util.UUID;

public record RemoverAmizadeCommand(
        UUID usuarioLogadoId,
        UUID amigoId
) {
}