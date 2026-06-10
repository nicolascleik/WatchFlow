package com.watchflow.WatchFlow.core.usecase.usuario;

import java.util.Set;
import java.util.UUID;

public record AtualizarPreferenciasCommand(
        UUID usuarioId,
        Set<UUID> categoriasIds
) {
}