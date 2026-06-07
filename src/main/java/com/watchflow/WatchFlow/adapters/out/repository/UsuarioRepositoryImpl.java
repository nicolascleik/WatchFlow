package com.watchflow.WatchFlow.adapters.out.repository;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioGateway usuarioGateway;

    public UsuarioRepositoryImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return Optional.ofNullable(usuarioGateway.buscarPorId(id));
    }
}
