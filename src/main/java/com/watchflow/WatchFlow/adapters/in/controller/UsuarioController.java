package com.watchflow.WatchFlow.adapters.in.controller;

import com.watchflow.WatchFlow.adapters.in.controller.request.AtualizarPerfilRequest;
import com.watchflow.WatchFlow.adapters.in.controller.request.AtualizarPreferenciasRequest;
import com.watchflow.WatchFlow.adapters.in.controller.request.CriarContaRequest;
import com.watchflow.WatchFlow.adapters.in.controller.response.UsuarioPerfilResponse;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPerfilUseCase;
import com.watchflow.WatchFlow.core.usecase.usuario.AtualizarPreferenciasUseCase;
import com.watchflow.WatchFlow.core.usecase.usuario.BuscarUsuarioUseCase;
import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchflow/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CriarContaUseCase criarContaUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;
    private final AtualizarPerfilUseCase atualizarPerfilUseCase;
    private final AtualizarPreferenciasUseCase atualizarPreferenciasUseCase;

    @PostMapping
    public ResponseEntity<Void> criarConta(@Valid @RequestBody CriarContaRequest request) {
        criarContaUseCase.executar(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPerfilResponse> buscarUsuario(@PathVariable UUID id) {
        UsuarioPerfilResponse response = UsuarioPerfilResponse.from(buscarUsuarioUseCase.executar(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioPerfilResponse>> listarUsuarios() {
        List<UsuarioPerfilResponse> response = buscarUsuarioUseCase.listarTodos().stream()
                .map(UsuarioPerfilResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me/perfil")
    public ResponseEntity<Void> atualizarPerfil(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @Valid @RequestBody AtualizarPerfilRequest request) {
        
        atualizarPerfilUseCase.executar(request.toCommand(usuarioLogadoId));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/preferencias")
    public ResponseEntity<Void> atualizarPreferencias(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @Valid @RequestBody AtualizarPreferenciasRequest request) {
        
        atualizarPreferenciasUseCase.executar(request.toCommand(usuarioLogadoId));
        return ResponseEntity.noContent().build();
    }
}