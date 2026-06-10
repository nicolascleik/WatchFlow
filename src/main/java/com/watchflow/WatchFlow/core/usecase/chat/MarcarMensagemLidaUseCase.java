package com.watchflow.WatchFlow.core.usecase.chat;

import java.util.UUID;

public interface MarcarMensagemLidaUseCase {
    void executar(UUID usuarioLogadoId, UUID amigoId);
}