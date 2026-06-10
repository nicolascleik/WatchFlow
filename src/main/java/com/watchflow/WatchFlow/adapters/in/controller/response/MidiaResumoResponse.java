package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;

import java.util.UUID;

public record MidiaResumoResponse(
        UUID id,
        Long tmdbId,
        String titulo,
        Integer anoLancamento,
        Double notaTmdb,
        String tipo
) {
    public static MidiaResumoResponse from(MidiaBase midia) {
        return new MidiaResumoResponse(
                midia.getId(),
                midia.getTmdbId(),
                midia.getTitulo(),
                midia.getAnoLancamento(),
                midia.getNotaTmdb(),
                midia.getTipo().name()
        );
    }
}