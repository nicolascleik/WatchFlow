package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.domain.midia.Serie;

import java.util.List;
import java.util.UUID;

public record MidiaDetalheResponse(
        UUID id,
        Long tmdbId,
        String titulo,
        String descricao,
        Integer anoLancamento,
        Double notaTmdb,
        List<String> plataformasDisponiveis,
        String tipo,
        Integer duracaoMinutos,
        Integer totalTemporadas
) {
    public static MidiaDetalheResponse from(MidiaBase midia) {
        // Pattern matching do Java para extrair dados das subclasses
        Integer duracao = midia instanceof Filme f ? f.getDuracaoMinutos() : null;
        Integer temporadas = midia instanceof Serie s ? s.getTotalTemporadas() : null;

        return new MidiaDetalheResponse(
                midia.getId(),
                midia.getTmdbId(),
                midia.getTitulo(),
                midia.getDescricao(),
                midia.getAnoLancamento(),
                midia.getNotaTmdb(),
                midia.getPlataformasDisponiveis(),
                midia.getTipo().name(),
                duracao,
                temporadas
        );
    }
}