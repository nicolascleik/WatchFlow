package com.watchflow.WatchFlow.core.domain.midia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class MidiaBase {
    
    protected UUID id;
    protected Long tmdbId;
    protected String titulo;
    protected String descricao;
    protected Integer anoLancamento;
    protected Double notaTmdb;
    protected List<String> plataformasDisponiveis;
    protected TipoMidia tipo;

    // Construtor protegido para ser usado apenas pelas classes filhas
    protected MidiaBase(UUID id, Long tmdbId, String titulo, String descricao, Integer anoLancamento, Double notaTmdb, List<String> plataformasDisponiveis, TipoMidia tipo) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.notaTmdb = notaTmdb;
        this.plataformasDisponiveis = plataformasDisponiveis != null ? new ArrayList<>(plataformasDisponiveis) : new ArrayList<>();
        this.tipo = tipo;
    }

    // Comportamento
    public void atualizarPlataformas(List<String> novasPlataformas) {
        this.plataformasDisponiveis = new ArrayList<>(novasPlataformas);
    }

    public void atualizarNota(Double novaNota) {
        this.notaTmdb = novaNota;
    }

    // Getters
    public UUID getId() { return id; }
    public Long getTmdbId() { return tmdbId; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Integer getAnoLancamento() { return anoLancamento; }
    public Double getNotaTmdb() { return notaTmdb; }
    public TipoMidia getTipo() { return tipo; }
    
    public List<String> getPlataformasDisponiveis() { 
        return Collections.unmodifiableList(plataformasDisponiveis); 
    }
}