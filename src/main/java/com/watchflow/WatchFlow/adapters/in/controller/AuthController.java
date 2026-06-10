package com.watchflow.WatchFlow.adapters.in.controller;

import com.watchflow.WatchFlow.adapters.in.controller.request.LoginRequest;
import com.watchflow.WatchFlow.adapters.in.controller.response.TokenResponse;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.usecase.usuario.FazerLoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchflow/auth")
@RequiredArgsConstructor
public class AuthController {

    private final FazerLoginUseCase fazerLoginUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = fazerLoginUseCase.executar(request.toCommand());
        
        // TODO: Substituir por serviço de geração de JWT real na Infraestrutura
        String tokenJwtSimulado = "jwt-token-para-" + usuario.getId().toString();
        
        return ResponseEntity.ok(new TokenResponse(tokenJwtSimulado));
    }
}