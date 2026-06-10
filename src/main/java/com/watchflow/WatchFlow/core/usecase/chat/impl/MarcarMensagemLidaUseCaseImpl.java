package com.watchflow.WatchFlow.core.usecase.chat.impl;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.gateway.ChatGateway;
import com.watchflow.WatchFlow.core.usecase.chat.MarcarMensagemLidaUseCase;

import java.util.List;
import java.util.UUID;

public class MarcarMensagemLidaUseCaseImpl implements MarcarMensagemLidaUseCase {

    private final ChatGateway chatGateway;

    public MarcarMensagemLidaUseCaseImpl(ChatGateway chatGateway) {
        this.chatGateway = chatGateway;
    }

    @Override
    public void executar(UUID usuarioLogadoId, UUID amigoId) {
        // Atenção aos parâmetros: busca mensagens que o amigo enviou para o usuário logado
        List<Mensagem> mensagensPendentes = chatGateway.buscarMensagensNaoLidas(amigoId, usuarioLogadoId);

        if (mensagensPendentes.isEmpty()) {
            return;
        }

        // Aplica o comportamento de negócio dentro da entidade
        for (Mensagem mensagem : mensagensPendentes) {
            mensagem.marcarComoLida();
        }

        // Devolve as entidades atualizadas para a persistência
        chatGateway.salvarEmLote(mensagensPendentes);
    }
}