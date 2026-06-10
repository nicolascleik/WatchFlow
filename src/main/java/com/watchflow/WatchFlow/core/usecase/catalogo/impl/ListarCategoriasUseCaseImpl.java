package com.watchflow.WatchFlow.core.usecase.catalogo.impl;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.ListarCategoriasUseCase;

import java.util.List;

public class ListarCategoriasUseCaseImpl implements ListarCategoriasUseCase {

    private final CatalogoGateway catalogoGateway;

    public ListarCategoriasUseCaseImpl(CatalogoGateway catalogoGateway) {
        this.catalogoGateway = catalogoGateway;
    }

    @Override
    public List<Categoria> executar() {
        return catalogoGateway.listarCategorias();
    }
}