package com.watchflow.WatchFlow.adapters.in.controller;

import com.watchflow.WatchFlow.adapters.in.controller.request.ResponderConviteRequest;
import com.watchflow.WatchFlow.adapters.in.controller.response.AmigoResponse;
import com.watchflow.WatchFlow.core.usecase.amizade.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchflow/amizades")
@RequiredArgsConstructor
public class AmizadeController {

    private final EnviarConviteUseCase enviarConviteUseCase;
    private final ResponderConviteUseCase responderConviteUseCase;
    private final RemoverAmizadeUseCase removerAmizadeUseCase;
    private final ListarAmigosUseCase listarAmigosUseCase;
    private final ListarConvitesUseCase listarConvitesUseCase;

    @PostMapping("/solicitar/{amigoId}")
    public ResponseEntity<Void> enviarConvite(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @PathVariable UUID amigoId) {
        
        enviarConviteUseCase.executar(new EnviarConviteCommand(usuarioLogadoId, amigoId));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/convites/{amizadeId}")
    public ResponseEntity<Void> responderConvite(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @PathVariable UUID amizadeId,
            @Valid @RequestBody ResponderConviteRequest request) {
        
        responderConviteUseCase.executar(request.toCommand(amizadeId, usuarioLogadoId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{amigoId}")
    public ResponseEntity<Void> removerAmizade(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @PathVariable UUID amigoId) {
        
        removerAmizadeUseCase.executar(new RemoverAmizadeCommand(usuarioLogadoId, amigoId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AmigoResponse>> listarAmigos(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId) {
        
        List<AmigoResponse> response = listarAmigosUseCase.executar(usuarioLogadoId).stream()
                .map(AmigoResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/convites/pendentes")
    public ResponseEntity<List<AmigoResponse>> listarConvitesPendentes(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId) {
        
        List<AmigoResponse> response = listarConvitesUseCase.executar(usuarioLogadoId).stream()
                .map(AmigoResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }
}