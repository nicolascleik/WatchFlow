package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class CategoriaEntity {

    @Id
    private UUID id;

    @Column(name = "tmdb_genre_id", unique = true, nullable = false)
    private Integer tmdbGenreId;

    @Column(nullable = false)
    private String nome;
}