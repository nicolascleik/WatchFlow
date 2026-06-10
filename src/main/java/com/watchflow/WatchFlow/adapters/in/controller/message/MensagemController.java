package com.watchflow.WatchFlow.adapters.in.controller.message;

import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.usecase.chat.EnviarMensagemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {

    private final EnviarMensagemUseCase enviarMensagemUseCase;
    private final UsuarioGateway usuarioGateway;

    public MensagemController(EnviarMensagemUseCase enviarMensagemUseCase, UsuarioGateway usuarioGateway) {
        this.enviarMensagemUseCase = enviarMensagemUseCase;
        this.usuarioGateway = usuarioGateway;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensagem(
            @RequestParam UUID remetenteId,
            @RequestParam UUID destinatarioId,
            @RequestBody String conteudo) {

        Usuario remetente = usuarioGateway.buscarPorId(remetenteId);
        Usuario destinatario = usuarioGateway.buscarPorId(destinatarioId);

        if (remetente == null || destinatario == null) {
            return ResponseEntity.badRequest().body("Erro: Remetente ou Destinatário não encontrado no banco de dados.");
        }

        try {
            enviarMensagemUseCase.executar(remetente, destinatario, conteudo);
            return ResponseEntity.ok("Mensagem enviada com sucesso!");
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}