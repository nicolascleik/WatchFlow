package com.watchflow.WatchFlow.adapters.in.controller.request;

import com.watchflow.WatchFlow.core.usecase.usuario.CriarContaCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarContaRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve ter 2 letras (Ex: SP)")
        String estado
) {
    public CriarContaCommand toCommand() {
        return new CriarContaCommand(nome, email, senha, cidade, estado);
    }
}