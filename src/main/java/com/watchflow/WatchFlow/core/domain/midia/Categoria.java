package com.watchflow.WatchFlow.core.domain.midia;

import java.util.UUID;

public class Categoria {
    private UUID id;
    private Integer tmdbGenreId;
    private String nome;

    public Categoria() {
    }

    public Categoria(UUID id, Integer tmdbGenreId, String nome) {
        this.id = id;
        this.tmdbGenreId = tmdbGenreId;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTmdbGenreId() {
        return tmdbGenreId;
    }

    public void setTmdbGenreId(Integer tmdbGenreId) {
        this.tmdbGenreId = tmdbGenreId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}