package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "episodios")
@Getter
@Setter
public class EpisodioEntity {

    @Id
    private UUID id;

    @Column(name = "serie_id", nullable = false)
    private UUID serieId;

    @Column(nullable = false)
    private Integer temporada;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String titulo;
}