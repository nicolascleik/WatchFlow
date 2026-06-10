package com.watchflow.WatchFlow.core.usecase.chat.impl;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.gateway.ChatGateway;
import com.watchflow.WatchFlow.core.usecase.chat.BuscarHistoricoUseCase;

import java.util.List;
import java.util.UUID;

public class BuscarHistoricoUseCaseImpl implements BuscarHistoricoUseCase {

    private static final int LIMITE_MENSAGENS_POR_PAGINA = 50;
    private final ChatGateway chatGateway;

    public BuscarHistoricoUseCaseImpl(ChatGateway chatGateway) {
        this.chatGateway = chatGateway;
    }

    @Override
    public List<Mensagem> executar(UUID usuarioLogadoId, UUID amigoId, int pagina) {
        return chatGateway.buscarHistorico(
                usuarioLogadoId, 
                amigoId, 
                LIMITE_MENSAGENS_POR_PAGINA, 
                pagina
        );
    }
}