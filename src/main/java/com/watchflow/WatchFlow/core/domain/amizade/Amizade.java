package com.watchflow.WatchFlow.core.domain.amizade;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Amizade {
    private UUID id;
    private UUID usuarioRemetenteId;
    private UUID usuarioDestinatarioId;

    @Builder.Default
    private StatusAmizade status = StatusAmizade.PENDENTE;

    @Builder.Default
    private LocalDateTime dataDeCriacao = LocalDateTime.now();
}
