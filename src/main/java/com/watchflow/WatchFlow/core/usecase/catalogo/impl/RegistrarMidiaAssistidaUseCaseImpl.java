package com.watchflow.WatchFlow.core.usecase.catalogo.impl;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
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
        Usuario usuario = usuarioGateway.buscarPorId(command.usuarioLogadoId());
        
        if (usuario == null) {
            throw new NaoEncontradoException("Usuário não encontrado.");
        }

        // 1. Busca os dados crus no TMDB
        MidiaBase midiaTmdb = catalogoGateway.buscarDetalhesTmdb(command.tmdbId());
        if (midiaTmdb == null) {
            throw new NaoEncontradoException("A mídia especificada não existe no TMDB.");
        }

        // 2. Transfere a responsabilidade para o Gateway salvar no nosso PostgreSQL e devolver a entidade com nosso UUID
        MidiaBase midiaLocal = catalogoGateway.buscarOuSalvarMidiaLocal(midiaTmdb);

        // 3. Aplica as regras de negócio de acordo com a herança
        if (midiaLocal instanceof Filme filme) {
            usuario.registrarFilmeAssistido(filme.getId());
        } else if (midiaLocal instanceof Serie) {
            // Se for uma série, pela regra de negócio, o usuário não assiste a série inteira num clique, 
            // ele precisa registrar episódios específicos (que seria uma feature futura do comando).
            throw new RegraNegocioException("Não é possível registrar uma série inteira como assistida. Registre episódios específicos.");
        }

        // 4. Salva o usuário atualizado
        usuarioGateway.salvar(usuario);
    }
}