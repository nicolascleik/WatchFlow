package com.watchflow.WatchFlow.core.gateway;

public interface CodificadorSenhaGateway {
    String codificar(String senhaBruta);
    boolean verificar(String senhaBruta, String senhaCodificada);
}