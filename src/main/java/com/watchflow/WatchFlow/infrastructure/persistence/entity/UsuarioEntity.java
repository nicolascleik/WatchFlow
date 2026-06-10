package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String cidade;
    private String estado;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_amigos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "amigo_id")
    private Set<UUID> amigosIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_categorias", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "categoria_id")
    private Set<UUID> categoriasFavoritasIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_filmes", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "filme_id")
    private Set<UUID> filmesAssistidosIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_episodios", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "episodio_id")
    private Set<UUID> episodiosAssistidosIds = new HashSet<>();
}