package com.watchflow.WatchFlow.adapters.in.controller.AmizadeController;

import com.watchflow.WatchFlow.adapters.out.repository.UsuarioRepository;
import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.usecase.amizade.SolicitarAmizadeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/amizades") 
public class AmizadeController {

    private final SolicitarAmizadeUseCase solicitarAmizadeUseCase;
    
    private final UsuarioRepository usuarioRepository; 

    public AmizadeController(SolicitarAmizadeUseCase solicitarAmizadeUseCase, UsuarioRepository usuarioRepository) {
        this.solicitarAmizadeUseCase = solicitarAmizadeUseCase;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarAmizade(
            @RequestParam UUID solicitanteId,
            @RequestParam UUID solicitadoId) {

        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new RuntimeException("Solicitante não encontrado!"));
        
        Usuario solicitado = usuarioRepository.findById(solicitadoId)
                .orElseThrow(() -> new RuntimeException("Usuário alvo não encontrado!"));

        solicitarAmizadeUseCase.executar(solicitante, solicitado);

        return ResponseEntity.ok("Pedido de amizade enviado com sucesso!");
    }
}