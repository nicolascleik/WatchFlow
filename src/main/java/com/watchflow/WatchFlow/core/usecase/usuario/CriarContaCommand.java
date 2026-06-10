package com.watchflow.WatchFlow.core.usecase.usuario;

public record CriarContaCommand(
        String nome,
        String email,
        String senhaBruta,
        String cidade,
        String estado
) {
}