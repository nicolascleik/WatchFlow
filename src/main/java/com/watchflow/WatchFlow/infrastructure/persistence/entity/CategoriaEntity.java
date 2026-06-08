package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private Integer tmdbGenreId;

    private String nome;

    public CategoriaEntity() {}

    public CategoriaEntity(Integer tmdbGenreId, String nome) {
        this.tmdbGenreId = tmdbGenreId;
        this.nome = nome;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Integer getTmdbGenreId() { return tmdbGenreId; }
    public void setTmdbGenreId(Integer tmdbGenreId) { this.tmdbGenreId = tmdbGenreId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}