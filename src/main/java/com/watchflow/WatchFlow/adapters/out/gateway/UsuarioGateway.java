package com.watchflow.watchflow.adapters.out.gateway;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.watchflow.watchflow.core.domain.usuario.Usuario;

public interface UsuarioGateway {
    Usuario buscarPorId(UUID id);
    List<Usuario> buscarUsuariosPorIds(Set<UUID> ids);
}