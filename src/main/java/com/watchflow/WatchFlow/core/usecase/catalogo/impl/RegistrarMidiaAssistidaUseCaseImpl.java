package com.watchflow.WatchFlow.core.usecase.catalogo.impl;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.domain.midia.TipoMidia;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaCommand;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaUseCase;

public class RegistrarMidiaAssistidaUseCaseImpl implements RegistrarMidiaAssistidaUseCase {

    private final CatalogoGateway catalogoGateway;
    private final UsuarioGateway usuarioGateway;

    public RegistrarMidiaAssistidaUseCaseImpl(CatalogoGateway catalogoGateway, UsuarioGateway usuarioGateway) {
        this.catalogoGateway = catalogoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void executar(RegistrarMidiaAssistidaCommand command) {
        Usuario usuario = usuarioGateway.buscarPorId(command.usuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        MidiaBase midiaDetalhes = catalogoGateway.buscarDetalhesTmdb(command.tmdbId());
        if (midiaDetalhes == null) {
            throw new IllegalArgumentException("Mídia não encontrada no TMDB.");
        }

        MidiaBase midiaLocal = catalogoGateway.buscarOuSalvarMidiaLocal(midiaDetalhes);

        if (command.tipoMidia() == TipoMidia.FILME) {
            usuario.registrarFilmeAssistido(midiaLocal.getId());
        } else if (command.tipoMidia() == TipoMidia.SERIE) {
            usuario.registrarEpisodioAssistido(midiaLocal.getId());
        }

        usuarioGateway.salvar(usuario);
    }
}