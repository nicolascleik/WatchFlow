package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import java.util.List;

public interface CatalogoGateway {
    List<MidiaBase> pesquisarPorTitulo(String titulo);
    MidiaBase buscarDetalhesTmdb(Long tmdbId);
    MidiaBase buscarOuSalvarMidiaLocal(MidiaBase midia);
    List<Categoria> listarCategorias();
}