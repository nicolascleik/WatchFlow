package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import com.watchflow.WatchFlow.core.domain.chat.StatusMensagem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mensagens")
@Getter
@Setter
public class MensagemEntity {

    @Id
    private UUID id;

    @Column(name = "remetente_id", nullable = false)
    private UUID remetenteId;

    @Column(name = "destinatario_id", nullable = false)
    private UUID destinatarioId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMensagem status;
}