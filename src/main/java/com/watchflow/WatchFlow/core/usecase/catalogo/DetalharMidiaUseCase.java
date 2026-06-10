package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;

public interface DetalharMidiaUseCase {
    MidiaBase executar(Long tmdbId);
}