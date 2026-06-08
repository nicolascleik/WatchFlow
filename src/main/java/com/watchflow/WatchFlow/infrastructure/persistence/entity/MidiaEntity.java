package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "medias")
public class MidiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private Long tmdbId;

    private String tipoMedia;

    public MidiaEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Long getTmdbId() { return tmdbId; }
    public void setTmdbId(Long tmdbId) { this.tmdbId = tmdbId; }

    public String getTipoMedia() { return tipoMedia; }
    public void setTipoMedia(String tipoMedia) { this.tipoMedia = tipoMedia; }
}