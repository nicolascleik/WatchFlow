package com.watchflow.WatchFlow.core.usecase.amizade.impl;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.amizade.RemoverAmizadeCommand;
import com.watchflow.WatchFlow.core.usecase.amizade.RemoverAmizadeUseCase;

import java.util.Arrays;

public class RemoverAmizadeUseCaseImpl implements RemoverAmizadeUseCase {

    private final AmizadeGateway amizadeGateway;
    private final UsuarioGateway usuarioGateway;

    public RemoverAmizadeUseCaseImpl(AmizadeGateway amizadeGateway, UsuarioGateway usuarioGateway) {
        this.amizadeGateway = amizadeGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(RemoverAmizadeCommand command) {
        Usuario usuarioLogado = usuarioGateway.buscarPorId(command.usuarioLogadoId());
        Usuario amigo = usuarioGateway.buscarPorId(command.amigoId());

        if (usuarioLogado == null || amigo == null) {
            throw new NaoEncontradoException("Um ou ambos os usuários não foram encontrados.");
        }

        // Desfaz o vínculo nos objetos de domínio usando os métodos puristas
        usuarioLogado.removerAmigo(amigo.getId());
        amigo.removerAmigo(usuarioLogado.getId());
        usuarioGateway.salvarTodos(Arrays.asList(usuarioLogado, amigo));

        // Encontra a entidade da amizade e a destrói via Gateway
        Amizade amizade = amizadeGateway.buscarPorUsuarios(usuarioLogado.getId(), amigo.getId());
        if (amizade != null) {
            amizadeGateway.excluir(amizade);
        }
    }
}