package com.watchflow.watchflow.core.usecase.usuario;

import com.watchflow.watchflow.core.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface BuscarUsuarioUseCase {
    Usuario executar(UUID id);
    List<Usuario> listarTodos();
}
