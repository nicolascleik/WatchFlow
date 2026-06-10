package com.watchflow.WatchFlow.core.usecase.amizade;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.amizade.StatusAmizade;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;

import java.util.UUID;

public class AceitarAmizadeUseCase {

    private final AmizadeGateway amizadeGateway;

    public AceitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        this.amizadeGateway = amizadeGateway;
    }

    public Amizade executar(UUID amizadeId) {
        Amizade amizade = amizadeGateway.buscarPorId(amizadeId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido de amizade não encontrado!"));

        amizade.setStatus(StatusAmizade.ACEITO);

        return amizadeGateway.salvar(amizade);
    }
}