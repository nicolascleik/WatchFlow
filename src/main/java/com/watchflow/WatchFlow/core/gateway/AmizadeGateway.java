package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import java.util.Optional;
import java.util.UUID;

public interface AmizadeGateway {
    Amizade salvar(Amizade amizade);
    Optional<Amizade> buscarPorId(UUID id); 
}