package com.watchflow.watchflow.adapters.out.repository;

import com.watchflow.watchflow.core.domain.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Optional<Usuario> findById(UUID id);
}
