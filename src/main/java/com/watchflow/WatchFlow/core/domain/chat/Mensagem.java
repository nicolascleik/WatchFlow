package com.watchflow.WatchFlow.core.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Mensagem {
    private UUID id;
    private UUID remetenteId;
    private UUID destinatarioId;
    private String conteudo;

    @Builder.Default
    private LocalDateTime dataDeEnvio = LocalDateTime.now();

    @Builder.Default
    private StatusMensagem status = StatusMensagem.NAO_LIDA;

    public void marcarComoLida() {
        this.status = StatusMensagem.LIDA;
    }
}
