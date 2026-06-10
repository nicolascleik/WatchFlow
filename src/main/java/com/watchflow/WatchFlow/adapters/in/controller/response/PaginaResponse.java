package com.watchflow.WatchFlow.adapters.in.controller.response;

import java.util.List;

public record PaginaResponse<T>(
        List<T> conteudo,
        int pagina
) {}