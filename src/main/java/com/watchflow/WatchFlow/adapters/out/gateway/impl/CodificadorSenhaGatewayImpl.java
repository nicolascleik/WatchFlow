package com.watchflow.WatchFlow.adapters.out.gateway.impl;

import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import org.springframework.stereotype.Component;

@Component
public class CodificadorSenhaGatewayImpl implements CodificadorSenhaGateway {
    @Override
    public String codificar(String senhaLimpa) {
        return senhaLimpa + "_hash_seguro_sqn";
    }
}
