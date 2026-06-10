package com.watchflow.WatchFlow.adapters.out.gateway.amizade;

import com.watchflow.WatchFlow.adapters.out.gateway.repository.AmizadeRepository;
import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AmizadeGatewayImpl implements AmizadeGateway {

    private final AmizadeRepository amizadeRepository;

    public AmizadeGatewayImpl(AmizadeRepository amizadeRepository) {
        this.amizadeRepository = amizadeRepository;
    }

    @Override
    public Amizade salvar(Amizade amizade) {
        return amizadeRepository.save(amizade);
    }

    @Override
    public Optional<Amizade> buscarPorId(UUID id) {
        return amizadeRepository.findById(id);
    }
}