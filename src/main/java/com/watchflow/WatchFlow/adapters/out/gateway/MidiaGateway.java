package com.watchflow.watchflow.adapters.out.gateway;

import java.util.List;

import com.watchflow.watchflow.core.domain.midia.MediaBase;
import com.watchflow.watchflow.core.usecase.catalogo.BuscarMidiasUseCase.CriterioOrdenacao;

public interface MidiaGateway {
    List<MediaBase> buscarPorTitulo(String titulo);
    List<MediaBase> buscarTodasOrdenadas(CriterioOrdenacao criterio);
}