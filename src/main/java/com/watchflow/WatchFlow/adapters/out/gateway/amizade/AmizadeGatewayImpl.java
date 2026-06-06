package com.watchflow.watchflow.adapters.out.gateway.amizade;

import com.watchflow.watchflow.adapters.out.gateway.repository.AmizadeRepository;
// import com.watchflow.watchflow.adapters.out.repository.AmizadeRepository;
import com.watchflow.watchflow.core.domain.amizade.Amizade;
import com.watchflow.watchflow.core.gateway.AmizadeGateway;
import org.springframework.stereotype.Component;

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
}