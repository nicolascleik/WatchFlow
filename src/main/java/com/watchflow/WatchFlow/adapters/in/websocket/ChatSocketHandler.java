package com.watchflow.WatchFlow.adapters.in.websocket;

import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatSocketHandler {

    private final EnviarMensagemUseCase enviarMensagemUseCase;

    // A rota completa que o cliente chamará será "/app/chat.enviar"
    @MessageMapping("/chat.enviar")
    public void enviarMensagem(@Payload WebSocketMessageRecord mensagemRecord) {
        enviarMensagemUseCase.executar(mensagemRecord.toCommand());
    }
}