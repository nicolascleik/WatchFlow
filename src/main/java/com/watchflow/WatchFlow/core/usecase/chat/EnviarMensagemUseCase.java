package com.watchflow.WatchFlow.core.usecase.chat;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.domain.chat.StatusMensagem;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.MensagemGateway;

import java.time.LocalDateTime;

public class EnviarMensagemUseCase {

    private final MensagemGateway mensagemGateway;

    public EnviarMensagemUseCase(MensagemGateway mensagemGateway) {
        this.mensagemGateway = mensagemGateway;
    }

    public Mensagem executar(Usuario remetente, Usuario destinatario, String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo da mensagem não pode estar vazio!");
        }

        Mensagem novaMensagem = Mensagem.builder()
                .remetente(remetente)
                .destinatario(destinatario)
                .conteudo(conteudo)
                .status(StatusMensagem.ENVIADA) 
                .dataEnvio(LocalDateTime.now())
                .build();

        return mensagemGateway.salvar(novaMensagem);
    }
}