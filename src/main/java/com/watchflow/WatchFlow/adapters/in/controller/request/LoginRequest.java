package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.usecase.usuario.FazerLoginCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank @Email String email,
        @NotBlank String senha
) {
    public FazerLoginCommand toCommand() {
        return new FazerLoginCommand(email, senha);
    }
}