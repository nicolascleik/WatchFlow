package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CriarContaUseCaseImpl implements CriarContaUseCase {
    private final UsuarioGateway usuarioGateway;
    private final CodificadorSenhaGateway codificadorSenhaGateway;

    @Override
    public void executar(CriarContaCommand comando) {
        boolean emailExiste = usuarioGateway.existePorEmail(comando.getEmail());

        if (emailExiste) {
            throw new IllegalArgumentException("Email já cadastrado na plataforma.");
        }

        String senhaCodificada = codificadorSenhaGateway.codificar(comando.getSenha());

        Usuario usuario = Usuario.builder()
                .id(UUID.randomUUID())
                .nome(comando.getNome())
                .email(comando.getEmail())
                .senha(senhaCodificada)
                .cidade(comando.getCidade())
                .estado(comando.getEstado())
                .build();

        usuarioGateway.salvar(usuario);
    }
}