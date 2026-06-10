package com.watchflow.WatchFlow.infrastructure.security;

import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodificadorSenhaGatewayImpl implements CodificadorSenhaGateway {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String codificar(String senhaBruta) {
        return passwordEncoder.encode(senhaBruta);
    }

    @Override
    public boolean verificar(String senhaBruta, String senhaCodificada) {
        return passwordEncoder.matches(senhaBruta, senhaCodificada);
    }
}