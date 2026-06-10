package com.watchflow.WatchFlow.core.usecase.chat.impl;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.ChatGateway;
import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemCommand;
import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemUseCase;

public class EnviarMensagemUseCaseImpl implements EnviarMensagemUseCase {

    private final ChatGateway chatGateway;
    private final AmizadeGateway amizadeGateway;

    public EnviarMensagemUseCaseImpl(ChatGateway chatGateway, AmizadeGateway amizadeGateway) {
        this.chatGateway = chatGateway;
        this.amizadeGateway = amizadeGateway;
    }

    @Override
    public void executar(EnviarMensagemCommand command) {
        // 1. Regra de Negócio Pura: Só envia se existir vínculo de amizade
        boolean saoAmigos = amizadeGateway.existeAmizade(command.remetenteId(), command.destinatarioId());
        
        if (!saoAmigos) {
            throw new RegraNegocioException("Não é possível enviar mensagem. Vocês não são amigos na plataforma.");
        }

        // 2. Criação da Entidade encapsulada
        Mensagem novaMensagem = Mensagem.escrever(
                command.remetenteId(),
                command.destinatarioId(),
                command.conteudo()
        );

        // 3. Persistência Física
        chatGateway.salvar(novaMensagem);

        // 4. Mágica do Tempo Real (Notifica a outra ponta do socket)
        chatGateway.despacharEventoTempoReal(novaMensagem);
    }
}