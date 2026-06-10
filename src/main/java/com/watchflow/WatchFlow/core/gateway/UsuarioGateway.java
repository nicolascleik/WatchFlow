package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioGateway {
    void salvar(Usuario usuario);
    void salvarTodos(List<Usuario> usuarios);
    Usuario buscarPorId(UUID id);
    Usuario buscarPorEmail(String email);
    boolean existePorEmail(String email);
    List<Usuario> listarTodos();
}