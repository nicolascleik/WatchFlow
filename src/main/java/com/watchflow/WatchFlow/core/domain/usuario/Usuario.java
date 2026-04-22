package com.watchflow.WatchFlow.core.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    private Set<UUID> filmesAssistidosIds = new HashSet<>();
    private Set<UUID> episodiosAssistidosIds = new HashSet<>();
    private Set<UUID> amigosIds = new HashSet<>();
    private Set<UUID> categoriasFavoritasIds = new HashSet<>();
}
