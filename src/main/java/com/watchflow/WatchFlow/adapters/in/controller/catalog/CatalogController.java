package com.watchflow.WatchFlow.adapters.in.controller.catalog;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.core.domain.midia.TipoMidia;
import com.watchflow.WatchFlow.core.usecase.catalogo.BuscarMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.DetalharMidiaUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.ListarCategoriasUseCase;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaCommand;
import com.watchflow.WatchFlow.core.usecase.catalogo.RegistrarMidiaAssistidaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catalogo")
public class CatalogController {

    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final RegistrarMidiaAssistidaUseCase registrarMidiaAssistidaUseCase;
    private final BuscarMidiaUseCase buscarMidiaUseCase;
    private final DetalharMidiaUseCase detalharMidiaUseCase;

    public CatalogController(ListarCategoriasUseCase listarCategoriasUseCase,
                             RegistrarMidiaAssistidaUseCase registrarMidiaAssistidaUseCase,
                             BuscarMidiaUseCase buscarMidiaUseCase,
                             DetalharMidiaUseCase detalharMidiaUseCase) {
        this.listarCategoriasUseCase = listarCategoriasUseCase;
        this.registrarMidiaAssistidaUseCase = registrarMidiaAssistidaUseCase;
        this.buscarMidiaUseCase = buscarMidiaUseCase;
        this.detalharMidiaUseCase = detalharMidiaUseCase;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = listarCategoriasUseCase.executar();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping("/assistidos")
    public ResponseEntity<Void> registrarAssistido(
            @RequestHeader("X-User-Id") String usuarioLogadoId,
            @RequestParam("tmdbId") Long tmdbId,
            @RequestParam("tipoMidia") String tipoMidia
    ) {
        UUID userUuid = UUID.fromString(usuarioLogadoId);
        TipoMidia tipo = TipoMidia.valueOf(tipoMidia.toUpperCase());

        RegistrarMidiaAssistidaCommand command = new RegistrarMidiaAssistidaCommand(userUuid, tmdbId, tipo);
        registrarMidiaAssistidaUseCase.executar(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/filmes/buscar")
    public ResponseEntity<List<Filme>> buscarFilmes(@RequestParam String titulo,
                                                    @RequestParam(defaultValue = "pt-BR") String idioma,
                                                    @RequestParam(defaultValue = "1") int pagina) {
        return ResponseEntity.ok(buscarMidiaUseCase.buscarFilmes(titulo, idioma, pagina));
    }

    @GetMapping("/filmes/populares")
    public ResponseEntity<List<Filme>> buscarFilmesPopulares(@RequestParam(defaultValue = "pt-BR") String idioma,
                                                             @RequestParam(defaultValue = "1") int pagina) {
        return ResponseEntity.ok(buscarMidiaUseCase.buscarFilmesPopulares(idioma, pagina));
    }

    @GetMapping("/filmes/{id}")
    public ResponseEntity<Filme> detalharFilme(@PathVariable Long id,
                                               @RequestParam(defaultValue = "pt-BR") String idioma) {
        return ResponseEntity.ok(detalharMidiaUseCase.detalharFilme(id, idioma));
    }

    @GetMapping("/series/buscar")
    public ResponseEntity<List<Serie>> buscarSeries(@RequestParam String titulo,
                                                    @RequestParam(defaultValue = "pt-BR") String idioma,
                                                    @RequestParam(defaultValue = "1") int pagina) {
        return ResponseEntity.ok(buscarMidiaUseCase.buscarSeries(titulo, idioma, pagina));
    }
}