package com.watchflow.WatchFlow.core.usecase.amizade.impl;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.ResponderConviteCommand;
import com.watchflow.WatchFlow.core.usecase.amizade.ResponderConviteUseCase;

import java.util.Arrays;

public class ResponderConviteUseCaseImpl implements ResponderConviteUseCase {

    private final AmizadeGateway amizadeGateway;
    private final UsuarioGateway usuarioGateway;

    public ResponderConviteUseCaseImpl(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        this.amizadeGateway = amizadeGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(ResponderConviteCommand command) {
        Amizade amizade = amizadeGateway.buscarPorId(command.amizadeId());

        if (amizade == null) {
            throw new NaoEncontradoException("Convite de amizade não encontrado.");
        }

        // Validação de Segurança (Anti-IDOR): O usuário logado é o destinatário do convite?
        if (!amizade.getSolicitadoId().equals(command.usuarioLogadoId())) {
            throw new RegraNegocioException("Você não tem permissão para responder a este convite.");
        }

        if (command.aceitar()) {
            amizade.aceitar(); // Muda o status na máquina de estados

            // Orquestração: Busca os dois usuários e cruza as amizades
            Usuario solicitante = usuarioGateway.buscarPorId(amizade.getSolicitanteId());
            Usuario solicitado = usuarioGateway.buscarPorId(amizade.getSolicitadoId());

            solicitante.adicionarAmigo(solicitado.getId());
            solicitado.adicionarAmigo(solicitante.getId());

            // Manda a Infra salvar tudo
            usuarioGateway.salvarTodos(Arrays.asList(solicitante, solicitado));
        } else {
            amizade.recusar();
        }

        amizadeGateway.salvar(amizade);
    }
}