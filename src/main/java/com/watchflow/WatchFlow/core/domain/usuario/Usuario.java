package com.watchflow.WatchFlow.core.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
