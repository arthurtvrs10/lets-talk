package com.backend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class HorarioPublisher {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private final SimpMessagingTemplate mensagens;

    public HorarioPublisher(SimpMessagingTemplate mensagens){
        this.mensagens = mensagens;
    }

    @Scheduled(fixedRate = 5000)
    public void publicasHorario(){
        String horario = LocalDateTime.now().format(FORMATO);
        Map<String, String> mensagem = Map.of("horario", horario);
        mensagens.convertAndSend("/topic/horario", mensagem);
    }
}
