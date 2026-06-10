package com.watchflow.WatchFlow.core.domain.midia;

import java.util.List;
import java.util.UUID;

public class Serie extends MidiaBase {

    private final Integer totalTemporadas;

    private Serie(UUID id, Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformasDisponiveis, Integer totalTemporadas) {
        super(id, tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformasDisponiveis, TipoMidia.SERIE);
        this.totalTemporadas = totalTemporadas;
    }

    // FÁBRICA: TMDB
    public static Serie criar(Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformas, Integer totalTemporadas) {
        return new Serie(UUID.randomUUID(), tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformas, totalTemporadas);
    }

    // FÁBRICA: DB
    public static Serie reconstruir(UUID id, Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformas, Integer totalTemporadas) {
        return new Serie(id, tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformas, totalTemporadas);
    }

    public Integer getTotalTemporadas() { return totalTemporadas; }
}