package com.watchflow.WatchFlow.core.domain.midia;

import java.util.UUID;

public class Episodio {

    private final UUID id;
    private final UUID serieId;
    private final Integer temporada;
    private final Integer numero;
    private final String titulo;

    private Episodio(UUID id, UUID serieId, Integer temporada, Integer numero, String titulo) {
        this.id = id;
        this.serieId = serieId;
        this.temporada = temporada;
        this.numero = numero;
        this.titulo = titulo;
    }

    public static Episodio criar(UUID serieId, Integer temporada, Integer numero, String titulo) {
        return new Episodio(UUID.randomUUID(), serieId, temporada, numero, titulo);
    }

    public static Episodio reconstruir(UUID id, UUID serieId, Integer temporada, Integer numero, String titulo) {
        return new Episodio(id, serieId, temporada, numero, titulo);
    }

    public UUID getId() { return id; }
    public UUID getSerieId() { return serieId; }
    public Integer getTemporada() { return temporada; }
    public Integer getNumero() { return numero; }
    public String getTitulo() { return titulo; }
}