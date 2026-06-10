package com.watchflow.WatchFlow.core.usecase.amizade;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface ListarConvitesUseCase {
    List<Usuario> executar(UUID usuarioLogadoId);
}