package com.watchflow.WatchFlow.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilita um broker em memória simples.
        // /topic -> Usado para broadcasts (ex: notificações globais).
        // /queue -> Usado para filas privadas (ex: chat 1-a-1).
        // /user  -> Usado para direcionar a um usuário específico pelo ID.
        config.enableSimpleBroker("/topic", "/queue", "/user");
        
        // Prefixo para mensagens que chegam do cliente para o servidor (ex: /app/chat.enviar)
        config.setApplicationDestinationPrefixes("/app");
        
        // Prefixo para roteamento privado
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // O ponto de entrada onde o Frontend vai fazer o "Handshake" (Aperto de mãos) HTTP 
        // antes de fazer o upgrade da conexão para o protocolo WebSocket.
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*") // Permite o Frontend acessar
                .withSockJS(); // Fallback caso o navegador não suporte WebSocket puro
    }
}