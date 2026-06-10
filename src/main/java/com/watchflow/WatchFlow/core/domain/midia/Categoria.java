package com.watchflow.WatchFlow.core.domain.midia;

import java.util.UUID;

public class Categoria {
    
    private final UUID id;
    private final Integer tmdbGenreId;
    private final String nome;

    private Categoria(UUID id, Integer tmdbGenreId, String nome) {
        this.id = id;
        this.tmdbGenreId = tmdbGenreId;
        this.nome = nome;
    }

    public static Categoria criar(Integer tmdbGenreId, String nome) {
        return new Categoria(UUID.randomUUID(), tmdbGenreId, nome);
    }

    public static Categoria reconstruir(UUID id, Integer tmdbGenreId, String nome) {
        return new Categoria(id, tmdbGenreId, nome);
    }

    public UUID getId() { return id; }
    public Integer getTmdbGenreId() { return tmdbGenreId; }
    public String getNome() { return nome; }
}