package com.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // define o ponto de entrada
        registry.addEndpoint("/ws") // padrão browser para registro endpoint inicial
                .setAllowedOriginPatterns("*") // libera qualquer tipo de origem
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // definir as mensagens, orquestração da comunicação
        registry.enableSimpleBroker("/topic", "/queue"); // ativa um broker interno simples na memoria que ccuida dos destinos que começam com /topic e /queue
        registry.setApplicationDestinationPrefixes("/app"); // todas as mensagem que o client enviar para um destino /app deve ser encaminhado pros metodos @message mapping
    }
}
