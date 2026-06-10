package com.watchflow.WatchFlow.adapters.in.controller;

import com.watchflow.WatchFlow.adapters.in.controller.request.RegistrarMidiaRequest;
import com.watchflow.WatchFlow.adapters.in.controller.response.CategoriaResponse;
import com.watchflow.WatchFlow.adapters.in.controller.response.MidiaDetalheResponse;
import com.watchflow.WatchFlow.adapters.in.controller.response.MidiaResumoResponse;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaCommand;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.DetalharMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.ListarCategoriasUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/watchflow/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final BuscarMidiaUseCase buscarMidiaUseCase;
    private final DetalharMidiaUseCase detalharMidiaUseCase;
    private final RegistrarMidiaAssistidaUseCase registrarMidiaAssistidaUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;

    @GetMapping("/buscar")
    public ResponseEntity<List<MidiaResumoResponse>> buscarMidia(
            @RequestParam("q") String titulo,
            @RequestParam(value = "pagina", defaultValue = "1") int pagina) {

        BuscarMidiaCommand command = new BuscarMidiaCommand(titulo, pagina);
        
        List<MidiaResumoResponse> response = buscarMidiaUseCase.executar(command)
                .stream()
                .map(MidiaResumoResponse::from)
                .toList();
                
        return ResponseEntity.ok(response);
    }

    @GetMapping("/midia/{tmdbId}")
    public ResponseEntity<MidiaDetalheResponse> detalharMidia(
            @PathVariable Long tmdbId) {

        MidiaDetalheResponse response = MidiaDetalheResponse.from(detalharMidiaUseCase.executar(tmdbId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        
        List<CategoriaResponse> response = listarCategoriasUseCase.executar()
                .stream()
                .map(CategoriaResponse::from)
                .toList();
                
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assistidos")
    public ResponseEntity<Void> registrarMidiaAssistida(
            @RequestHeader("X-User-Id") UUID usuarioLogadoId,
            @Valid @RequestBody RegistrarMidiaRequest request) {

        registrarMidiaAssistidaUseCase.executar(request.toCommand(usuarioLogadoId));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}