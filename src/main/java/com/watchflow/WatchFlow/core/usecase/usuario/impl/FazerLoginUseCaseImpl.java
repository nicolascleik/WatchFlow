package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.CodificadorSenhaGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.FazerLoginCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.FazerLoginUseCase;

public class FazerLoginUseCaseImpl implements FazerLoginUseCase {

    private final UsuarioGateway usuarioGateway;
    private final CodificadorSenhaGateway codificadorSenhaGateway;

    public FazerLoginUseCaseImpl(UsuarioGateway usuarioGateway, CodificadorSenhaGateway codificadorSenhaGateway) {
        this.usuarioGateway = usuarioGateway;
        this.codificadorSenhaGateway = codificadorSenhaGateway;
    }

    @Override
    public Usuario executar(FazerLoginCommand command) {
        Usuario usuario = usuarioGateway.buscarPorEmail(command.email());

        if (usuario == null) {
            throw new NaoEncontradoException("Nenhuma conta encontrada para este e-mail.");
        }

        if (!codificadorSenhaGateway.verificar(command.senhaBruta(), usuario.getSenha())) {
            throw new RegraNegocioException("Credenciais inválidas.");
        }

        return usuario;
    }
}