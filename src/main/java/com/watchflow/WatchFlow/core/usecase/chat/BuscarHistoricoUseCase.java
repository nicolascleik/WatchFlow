package com.watchflow.WatchFlow.core.usecase.chat;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;

import java.util.List;
import java.util.UUID;

public interface BuscarHistoricoUseCase {
    List<Mensagem> executar(UUID usuarioLogadoId, UUID amigoId, int pagina);
}