package com.watchflow.WatchFlow.core.domain.chat;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mensagem {

    private final UUID id;
    private final UUID remetenteId;
    private final UUID destinatarioId;
    private final String conteudo;
    private final LocalDateTime dataEnvio;
    private StatusMensagem status;

    private Mensagem(UUID id, UUID remetenteId, UUID destinatarioId, String conteudo, LocalDateTime dataEnvio, StatusMensagem status) {
        this.id = id;
        this.remetenteId = remetenteId;
        this.destinatarioId = destinatarioId;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
        this.status = status;
    }

    // FÁBRICA: Usada quando o usuário digita e envia uma nova mensagem
    public static Mensagem escrever(UUID remetenteId, UUID destinatarioId, String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo da mensagem não pode ser vazio.");
        }
        return new Mensagem(
                UUID.randomUUID(), 
                remetenteId, 
                destinatarioId, 
                conteudo, 
                LocalDateTime.now(), 
                StatusMensagem.ENVIADA
        );
    }

    // FÁBRICA: Usada pelo Mapper para reconstruir o objeto que veio do banco de dados
    public static Mensagem reconstruir(UUID id, UUID remetenteId, UUID destinatarioId, String conteudo, LocalDateTime dataEnvio, StatusMensagem status) {
        return new Mensagem(id, remetenteId, destinatarioId, conteudo, dataEnvio, status);
    }

    // Comportamento
    public void marcarComoLida() {
        this.status = StatusMensagem.LIDA;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getRemetenteId() { return remetenteId; }
    public UUID getDestinatarioId() { return destinatarioId; }
    public String getConteudo() { return conteudo; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public StatusMensagem getStatus() { return status; }
}