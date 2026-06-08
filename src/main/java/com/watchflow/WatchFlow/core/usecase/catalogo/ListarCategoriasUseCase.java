package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import java.util.List;

public interface ListarCategoriasUseCase {
    List<Categoria> executar();
}