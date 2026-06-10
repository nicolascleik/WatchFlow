package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "midias")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_midia", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class MidiaEntity {

    @Id
    private UUID id;

    @Column(name = "tmdb_id", unique = true, nullable = false)
    private Long tmdbId;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    @Column(name = "nota_tmdb")
    private Double notaTmdb;

    // Cria uma tabela auxiliar automática no banco apenas para listar os nomes dos streamings
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "midia_plataformas", joinColumns = @JoinColumn(name = "midia_id"))
    @Column(name = "plataforma")
    private List<String> plataformasDisponiveis;
}