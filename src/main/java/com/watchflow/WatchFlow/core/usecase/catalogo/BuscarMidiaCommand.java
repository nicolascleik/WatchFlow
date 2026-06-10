package com.watchflow.WatchFlow.core.usecase.catalogo;

public record BuscarMidiaCommand(
        String titulo,
        int pagina
) {
}