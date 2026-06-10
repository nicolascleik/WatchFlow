package com.watchflow.WatchFlow.core.usecase.catalogo.impl;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaCommand;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaUseCase;

import java.util.List;

public class BuscarMidiaUseCaseImpl implements BuscarMidiaUseCase {

    private final CatalogoGateway catalogoGateway;

    public BuscarMidiaUseCaseImpl(CatalogoGateway catalogoGateway) {
        this.catalogoGateway = catalogoGateway;
    }

    @Override
    public List<MidiaBase> executar(BuscarMidiaCommand command) {
        // O Gateway será responsável por bater na API do TMDB usando a string de busca.
        // A página do command poderá ser repassada futuramente caso o Gateway expanda sua assinatura.
        return catalogoGateway.pesquisarPorTitulo(command.titulo());
    }
}