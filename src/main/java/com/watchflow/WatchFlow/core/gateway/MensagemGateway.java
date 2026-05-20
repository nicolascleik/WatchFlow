package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;

public interface MensagemGateway {
    Mensagem salvar(Mensagem mensagem);
}