package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

public interface UsuarioGateway {
    void salvar(Usuario usuario);
    boolean existePorEmail(String email);
}
