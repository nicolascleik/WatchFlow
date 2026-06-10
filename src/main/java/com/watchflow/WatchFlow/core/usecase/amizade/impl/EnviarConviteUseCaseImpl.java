package com.watchflow.WatchFlow.core.usecase.amizade.impl;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.EnviarConviteCommand;
import com.watchflow.WatchFlow.core.usecase.amizade.EnviarConviteUseCase;

public class EnviarConviteUseCaseImpl implements EnviarConviteUseCase {

    private final AmizadeGateway amizadeGateway;
    private final UsuarioGateway usuarioGateway;

    public EnviarConviteUseCaseImpl(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        this.amizadeGateway = amizadeGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(EnviarConviteCommand command) {
        // Regra de negócio: Impede convites duplicados
        if (amizadeGateway.existeAmizade(command.solicitanteId(), command.solicitadoId())) {
            throw new RegraNegocioException("Já existe um convite ou amizade entre estes usuários.");
        }

        Usuario solicitante = usuarioGateway.buscarPorId(command.solicitanteId());
        Usuario solicitado = usuarioGateway.buscarPorId(command.solicitadoId());

        if (solicitante == null || solicitado == null) {
            throw new NaoEncontradoException("Usuário solicitante ou solicitado não encontrado no sistema.");
        }

        Amizade novaAmizade = Amizade.solicitar(solicitante.getId(), solicitado.getId());
        
        amizadeGateway.salvar(novaAmizade);
    }
}