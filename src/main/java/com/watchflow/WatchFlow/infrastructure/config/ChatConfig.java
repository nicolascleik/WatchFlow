package com.watchflow.WatchFlow.infrastructure.config;

import com.watchflow.WatchFlow.core.gateway.MensagemGateway;
import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public EnviarMensagemUseCase enviarMensagemUseCase(MensagemGateway mensagemGateway) {
        return new EnviarMensagemUseCase(mensagemGateway);
    }
}