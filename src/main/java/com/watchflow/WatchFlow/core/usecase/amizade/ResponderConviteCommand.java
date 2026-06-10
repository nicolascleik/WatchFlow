package com.watchflow.WatchFlow.core.usecase.amizade;

import java.util.UUID;

public record ResponderConviteCommand(
        UUID amizadeId,
        UUID usuarioLogadoId,
        boolean aceitar
) {
}