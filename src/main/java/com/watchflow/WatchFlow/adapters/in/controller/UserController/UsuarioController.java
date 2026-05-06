package com.watchflow.WatchFlow.adapters.in.controller.UserController;

import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchflow/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final CriarContaUseCase criarContaUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody CriarContaCommand contaCommand){
        criarContaUseCase.executar(contaCommand);
    }
}
