package com.watchflow.WatchFlow.adapters.in.websocket;

import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemCommand;

import java.util.UUID;

public record WebSocketMessageRecord(
        UUID remetenteId,
        UUID destinatarioId,
        String conteudo
) {
    public EnviarMensagemCommand toCommand() {
        return new EnviarMensagemCommand(remetenteId, destinatarioId, conteudo);
    }
}