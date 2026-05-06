package com.watchflow.WatchFlow.core.domain.usuario;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    private String cidade;
    private String estado;

    private Set<UUID> filmesAssistidosIds = new HashSet<>();
    private Set<UUID> episodiosAssistidosIds = new HashSet<>();
    private Set<UUID> amigosIds = new HashSet<>();
    private Set<UUID> categoriasFavoritasIds = new HashSet<>();
}
