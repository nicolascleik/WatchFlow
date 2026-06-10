package com.watchflow.WatchFlow.core.usecase.usuario;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface BuscarUsuarioUseCase {
    Usuario executar(UUID id);
    List<Usuario> listarTodos();
}