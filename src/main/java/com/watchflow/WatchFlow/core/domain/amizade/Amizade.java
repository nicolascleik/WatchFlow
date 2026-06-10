package com.watchflow.WatchFlow.core.domain.amizade;

import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Amizade {

    private final UUID id;
    private final UUID solicitanteId;
    private final UUID solicitadoId;
    private StatusAmizade status;
    private final LocalDateTime dataSolicitacao;

    // Construtor privado para forçar o uso das Factories
    private Amizade(UUID id, UUID solicitanteId, UUID solicitadoId, StatusAmizade status, LocalDateTime dataSolicitacao) {
        this.id = id;
        this.solicitanteId = solicitanteId;
        this.solicitadoId = solicitadoId;
        this.status = status;
        this.dataSolicitacao = dataSolicitacao;
    }

    // FÁBRICA: Criação de um novo pedido de amizade
    public static Amizade solicitar(UUID solicitanteId, UUID solicitadoId) {
        if (solicitanteId == null || solicitadoId == null) {
            throw new RegraNegocioException("Os IDs do solicitante e do solicitado não podem ser nulos.");
        }
        if (solicitanteId.equals(solicitadoId)) {
            throw new RegraNegocioException("Um usuário não pode enviar um convite de amizade para si mesmo.");
        }

        return new Amizade(
                UUID.randomUUID(),
                solicitanteId,
                solicitadoId,
                StatusAmizade.PENDENTE,
                LocalDateTime.now()
        );
    }

    // FÁBRICA: Reconstrução a partir do banco de dados (usado pelo Mapper na Infraestrutura)
    public static Amizade reconstruir(UUID id, UUID solicitanteId, UUID solicitadoId, StatusAmizade status, LocalDateTime dataSolicitacao) {
        return new Amizade(id, solicitanteId, solicitadoId, status, dataSolicitacao);
    }

    // MÉTODOS DE NEGÓCIO (Máquina de Estados)

    public void aceitar() {
        if (this.status != StatusAmizade.PENDENTE) {
            throw new RegraNegocioException("Apenas convites pendentes podem ser aceitos.");
        }
        this.status = StatusAmizade.ACEITA;
    }

    public void recusar() {
        if (this.status != StatusAmizade.PENDENTE) {
            throw new RegraNegocioException("Apenas convites pendentes podem ser recusados.");
        }
        this.status = StatusAmizade.RECUSADA;
    }

    public void bloquear() {
        this.status = StatusAmizade.BLOQUEADA;
    }

    // GETTERS
    public UUID getId() { return id; }
    public UUID getSolicitanteId() { return solicitanteId; }
    public UUID getSolicitadoId() { return solicitadoId; }
    public StatusAmizade getStatus() { return status; }
    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
}