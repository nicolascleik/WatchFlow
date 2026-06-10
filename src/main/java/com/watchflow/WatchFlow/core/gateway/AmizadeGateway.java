package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;

import java.util.List;
import java.util.UUID;

public interface AmizadeGateway {
    void salvar(Amizade amizade);
    void excluir(Amizade amizade);
    Amizade buscarPorId(UUID id);
    List<Amizade> buscarPendentes(UUID usuarioSolicitadoId);
    boolean existeAmizade(UUID usuario1, UUID usuario2);
    Amizade buscarPorUsuarios(UUID usuario1, UUID usuario2);
}