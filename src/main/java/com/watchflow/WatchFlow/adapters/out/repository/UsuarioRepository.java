package com.watchflow.WatchFlow.adapters.out.repository;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Optional<Usuario> findById(UUID id);
}
