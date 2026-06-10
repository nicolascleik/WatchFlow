package com.watchflow.WatchFlow.core.usecase.amizade;

import java.util.UUID;

public record EnviarConviteCommand(
        UUID solicitanteId,
        UUID solicitadoId
) {
}