package com.watchflow.WatchFlow.core.usecase.usuario.impl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPreferenciasCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPreferenciasUseCase;

public class AtualizarPreferenciasUseCaseImpl implements AtualizarPreferenciasUseCase {

    private final UsuarioGateway usuarioGateway;
    private final CatalogoGateway catalogoGateway;

    public AtualizarPreferenciasUseCaseImpl(UsuarioGateway usuarioGateway, CatalogoGateway catalogoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.catalogoGateway = catalogoGateway;
    }

    @Override
    public void executar(AtualizarPreferenciasCommand command) {
        Usuario usuario = usuarioGateway.buscarPorId(command.usuarioId());

        if (usuario == null) {
            throw new NaoEncontradoException("Usuário não encontrado.");
        }

        // Validação de negócio: Verifica se os IDs informados são de categorias válidas
        boolean categoriasValidas = catalogoGateway.todasCategoriasExistem(command.categoriasIds());
        if (!categoriasValidas) {
            throw new RegraNegocioException("Uma ou mais categorias selecionadas não existem no catálogo.");
        }

        usuario.atualizarCategorias(command.categoriasIds());

        usuarioGateway.salvar(usuario);
    }
}