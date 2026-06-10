package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensagemResponse(
        UUID id,
        UUID remetenteId,
        UUID destinatarioId,
        String conteudo,
        LocalDateTime dataEnvio,
        String status
) {
    public static MensagemResponse from(Mensagem mensagem) {
        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getRemetenteId(),
                mensagem.getDestinatarioId(),
                mensagem.getConteudo(),
                mensagem.getDataEnvio(),
                mensagem.getStatus().name()
        );
    }
}