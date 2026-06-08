package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import java.util.List;

public interface MidiaGateway {
    List<MidiaBase> buscarPorTitulo(String titulo);
    List<MidiaBase> buscarTodas();
}