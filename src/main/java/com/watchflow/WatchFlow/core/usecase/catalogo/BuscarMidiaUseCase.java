package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;

import java.util.List;

public interface BuscarMidiaUseCase {
    List<MidiaBase> executar(BuscarMidiaCommand command);
}