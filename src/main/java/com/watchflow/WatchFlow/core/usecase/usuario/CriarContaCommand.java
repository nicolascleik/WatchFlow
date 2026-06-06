package com.watchflow.watchflow.core.usecase.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class CriarContaCommand {
    private String nome;
    private String email;
    private String senha;
    private String cidade;
    private String estado;
}
