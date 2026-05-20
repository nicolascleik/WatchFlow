package com.watchflow.WatchFlow.adapters.in.controller.UserController;

import com.watchflow.WatchFlow.adapters.in.controller.UserController.UsuarioResponse;
import com.watchflow.WatchFlow.core.usecase.usuario.BuscarUsuarioUseCase;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaCommand;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchflow/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final CriarContaUseCase criarContaUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody CriarContaCommand contaCommand){
        criarContaUseCase.executar(contaCommand);
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscarUsuario(@PathVariable UUID id) {
        return UsuarioResponse.from(buscarUsuarioUseCase.executar(id));
    }

    @GetMapping
    public List<UsuarioResponse> listarUsuarios() {
        return buscarUsuarioUseCase.listarTodos().stream()
                .map(UsuarioResponse::from)
                .toList();
    }
}
