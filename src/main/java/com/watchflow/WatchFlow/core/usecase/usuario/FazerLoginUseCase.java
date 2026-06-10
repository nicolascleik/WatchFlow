package com.watchflow.WatchFlow.core.usecase.usuario;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;

public interface FazerLoginUseCase {
    Usuario executar(FazerLoginCommand command);
}