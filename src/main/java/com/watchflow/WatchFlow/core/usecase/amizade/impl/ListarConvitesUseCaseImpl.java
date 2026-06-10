package com.watchflow.WatchFlow.core.usecase.amizade.impl;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.ListarConvitesUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListarConvitesUseCaseImpl implements ListarConvitesUseCase {

    private final AmizadeGateway amizadeGateway;
    private final UsuarioGateway usuarioGateway;

    public ListarConvitesUseCaseImpl(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        this.amizadeGateway = amizadeGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public List<Usuario> executar(UUID usuarioLogadoId) {
        // Busca as amizades pendentes onde o usuário logado é o destinatário (solicitado)
        List<Amizade> convitesPendentes = amizadeGateway.buscarPendentes(usuarioLogadoId);

        // Mapeia os IDs dos solicitantes para as entidades Usuario completas
        return convitesPendentes.stream()
                .map(Amizade::getSolicitanteId)
                .map(usuarioGateway::buscarPorId)
                .filter(usuario -> usuario != null)
                .collect(Collectors.toList());
    }
}