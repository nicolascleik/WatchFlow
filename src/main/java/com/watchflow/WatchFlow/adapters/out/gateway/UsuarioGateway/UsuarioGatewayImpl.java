package com.watchflow.WatchFlow.adapters.out.gateway.UsuarioGateway;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void salvar(Usuario usuario) {
        if(!existePorEmail(usuario.getEmail())){
            usuarios.add(usuario);
        }
    }

    @Override
    public boolean existePorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
