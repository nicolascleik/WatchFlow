package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioGateway {
    void salvar(Usuario usuario);
    boolean existePorEmail(String email);
    Usuario buscarPorId(UUID id);
    List<Usuario> buscarTodos();
}
