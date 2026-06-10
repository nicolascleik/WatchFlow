package com.watchflow.WatchFlow.core.domain.midia;

import java.util.List;
import java.util.UUID;

public class Filme extends MidiaBase {

    private final Integer duracaoMinutos;

    private Filme(UUID id, Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformasDisponiveis, Integer duracaoMinutos) {
        super(id, tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformasDisponiveis, TipoMidia.FILME);
        this.duracaoMinutos = duracaoMinutos;
    }

    // FÁBRICA: Usado na tradução dos dados do TMDB
    public static Filme criar(Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformas, Integer duracaoMinutos) {
        return new Filme(UUID.randomUUID(), tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformas, duracaoMinutos);
    }

    // FÁBRICA: Usado pelo Mapper de Persistência
    public static Filme reconstruir(UUID id, Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformas, Integer duracaoMinutos) {
        return new Filme(id, tmdbId, titulo, descricao, anoLancamento, notaTmdb, plataformas, duracaoMinutos);
    }

    public Integer getDuracaoMinutos() { return duracaoMinutos; }
}