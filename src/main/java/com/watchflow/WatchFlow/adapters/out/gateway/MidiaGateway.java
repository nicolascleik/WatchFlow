package com.watchflow.WatchFlow.adapters.out.gateway;

import java.util.List;

import com.watchflow.WatchFlow.core.domain.midia.MediaBase;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiasUseCase.CriterioOrdenacao;

public interface MidiaGateway {
    List<MediaBase> buscarPorTitulo(String titulo);
    List<MediaBase> buscarTodasOrdenadas(CriterioOrdenacao criterio);
}