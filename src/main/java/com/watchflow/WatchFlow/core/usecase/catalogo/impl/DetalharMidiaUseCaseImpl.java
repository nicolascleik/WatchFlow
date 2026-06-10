package com.watchflow.WatchFlow.core.usecase.catalogo.impl;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.DetalharMidiaUseCase;

public class DetalharMidiaUseCaseImpl implements DetalharMidiaUseCase {

    private final CatalogoGateway catalogoGateway;

    public DetalharMidiaUseCaseImpl(CatalogoGateway catalogoGateway) {
        this.catalogoGateway = catalogoGateway;
    }

    @Override
    public MidiaBase executar(Long tmdbId) {
        MidiaBase midia = catalogoGateway.buscarDetalhesTmdb(tmdbId);
        
        if (midia == null) {
            throw new NaoEncontradoException("Mídia não encontrada no catálogo externo.");
        }
        
        return midia;
    }
}