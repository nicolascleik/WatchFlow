package com.watchflow.WatchFlow.adapters.in.controller;

import com.watchflow.WatchFlow.adapters.in.controller.response.MensagemResponse;
import com.watchflow.WatchFlow.adapters.in.controller.response.PaginaResponse;
import com.watchflow.WatchFlow.core.usecase.chat.BuscarHistoricoUseCase;
import com.watchflow.WatchFlow.core.usecase.chat.MarcarMensagemLidaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchflow/chat")
@RequiredArgsConstructor
public class ChatController {

    private final BuscarHistoricoUseCase buscarHistoricoUseCase;
    private final MarcarMensagemLidaUseCase marcarMensagemLidaUseCase;

    @GetMapping("/historico/{amigoId}")
    public ResponseEntity<PaginaResponse<MensagemResponse>> buscarHistorico(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @PathVariable UUID amigoId,
            @RequestParam(value = "pagina", defaultValue = "1") int pagina) {

        List<MensagemResponse> mensagens = buscarHistoricoUseCase.executar(usuarioLogadoId, amigoId, pagina)
                .stream()
                .map(MensagemResponse::from)
                .toList();

        return ResponseEntity.ok(new PaginaResponse<>(mensagens, pagina));
    }

    @PutMapping("/lidas/{amigoId}")
    public ResponseEntity<Void> marcarComoLidas(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @PathVariable UUID amigoId) {

        marcarMensagemLidaUseCase.executar(usuarioLogadoId, amigoId);
        return ResponseEntity.noContent().build();
    }
}