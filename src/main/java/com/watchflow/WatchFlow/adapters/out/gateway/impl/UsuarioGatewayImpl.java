package com.watchflow.WatchFlow.adapters.out.gateway.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void salvar(Usuario usuario) {
        if (!existePorEmail(usuario.getEmail())) {
            usuarios.add(usuario);
        }
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarios.stream()
                .anyMatch(usuario -> usuario.getEmail().equals(email));
    }

    @Override
    public Usuario buscarPorId(UUID id) {
        return usuarios.stream()
                .filter(usuario -> id.equals(usuario.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public List<Usuario> buscarUsuariosPorIds(Set<UUID> amigosIds) {
        return List.of();
    }
}
