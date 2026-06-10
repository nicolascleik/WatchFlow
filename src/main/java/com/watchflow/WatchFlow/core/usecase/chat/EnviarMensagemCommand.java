package com.watchflow.WatchFlow.core.usecase.chat;

import java.util.UUID;

public record EnviarMensagemCommand(
        UUID remetenteId,
        UUID destinatarioId,
        String conteudo
) {
}