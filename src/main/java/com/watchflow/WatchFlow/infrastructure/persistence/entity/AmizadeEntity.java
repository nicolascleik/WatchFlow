package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import com.watchflow.WatchFlow.core.domain.amizade.StatusAmizade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "amizades")
@Getter
@Setter
public class AmizadeEntity {

    @Id
    private UUID id;

    @Column(name = "solicitante_id", nullable = false)
    private UUID solicitanteId;

    @Column(name = "solicitado_id", nullable = false)
    private UUID solicitadoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAmizade status;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao;
}