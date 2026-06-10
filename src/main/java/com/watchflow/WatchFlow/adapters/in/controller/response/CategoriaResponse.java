package com.watchflow.WatchFlow.adapters.in.controller.response;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;

import java.util.UUID;

public record CategoriaResponse(
        UUID id,
        Integer tmdbGenreId,
        String nome
) {
    public static CategoriaResponse from(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getTmdbGenreId(),
                categoria.getNome()
        );
    }
}