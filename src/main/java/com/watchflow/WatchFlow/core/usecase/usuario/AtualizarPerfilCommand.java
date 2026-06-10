package com.watchflow.WatchFlow.core.usecase.usuario;

import java.util.UUID;

public record AtualizarPerfilCommand(
        UUID usuarioId,
        String nome,
        String cidade,
        String estado
) {
}