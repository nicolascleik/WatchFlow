package com.watchflow.WatchFlow.adapters.in.controller.friendship;

import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.usecase.amizade.AceitarAmizadeUseCase;
import com.watchflow.WatchFlow.core.usecase.amizade.BuscarListaAmigosUseCase;
import com.watchflow.WatchFlow.core.usecase.amizade.SolicitarAmizadeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/amizades")
public class AmizadeController {

    private final SolicitarAmizadeUseCase solicitarAmizadeUseCase;
    private final BuscarListaAmigosUseCase buscarListaAmigosUseCase;
    private final AceitarAmizadeUseCase aceitarAmizadeUseCase;
    private final UsuarioGateway usuarioGateway;

    public AmizadeController(SolicitarAmizadeUseCase solicitarAmizadeUseCase, 
                              BuscarListaAmigosUseCase buscarListaAmigosUseCase,
                              AceitarAmizadeUseCase aceitarAmizadeUseCase,
                              UsuarioGateway usuarioGateway) {
        this.solicitarAmizadeUseCase = solicitarAmizadeUseCase;
        this.buscarListaAmigosUseCase = buscarListaAmigosUseCase;
        this.aceitarAmizadeUseCase = aceitarAmizadeUseCase;
        this.usuarioGateway = usuarioGateway;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarAmizade(
            @RequestParam UUID solicitanteId,
            @RequestParam UUID solicitadoId) {

        Usuario solicitante = usuarioGateway.buscarPorId(solicitanteId);
        Usuario solicitado = usuarioGateway.buscarPorId(solicitadoId);

        if (solicitante == null || solicitado == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        solicitarAmizadeUseCase.executar(solicitante, solicitado);
        return ResponseEntity.ok("Pedido de amizade enviado com sucesso!");
    }

    @PatchMapping("/{amizadeId}/aceitar")
    public ResponseEntity<String> aceitarAmizade(@PathVariable UUID amizadeId) {
        try {
            aceitarAmizadeUseCase.executar(amizadeId);
            return ResponseEntity.ok("Amizade aceita com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<List<UsuarioDTO>> listarAmigos(@PathVariable UUID usuarioId) {
        // Busca os usuários completos
        List<Usuario> amigos = buscarListaAmigosUseCase.executar(usuarioId);
        
        List<UsuarioDTO> amigosSeguros = amigos.stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail()))
                .toList();

        return ResponseEntity.ok(amigosSeguros);
    }

    public record UsuarioDTO(UUID id, String nome, String email) {}
}