package com.watchflow.WatchFlow.core.usecase.usuario;

public record FazerLoginCommand(
        String email,
        String senhaBruta
) {
}