package com.watchflow.WatchFlow.core.usecase.amizade;

public class SolicitarAmizadeUseCase {
    
}
package com.watchflow.WatchFlow.core.usecase.amizade;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.amizade.StatusAmizade;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SolicitarAmizadeUseCase {

    private final AmizadeGateway amizadeGateway;

    public SolicitarAmizadeUseCase(AmizadeGateway amizadeGateway) {
        this.amizadeGateway = amizadeGateway;
    }

    public Amizade executar(Usuario solicitante, Usuario solicitado) {
        if (solicitante.getId().equals(solicitado.getId())) {
            throw new IllegalArgumentException("Você não pode enviar um convite de amizade para si próprio!");
        }

        Amizade novaAmizade = Amizade.builder()
                .solicitante(solicitante)
                .solicitado(solicitado)
                .status(StatusAmizade.PENDENTE) // A amizade nasce sempre como PENDENTE
                .dataSolicitacao(LocalDateTime.now())
                .build();
        return amizadeGateway.salvar(novaAmizade);
    }
}