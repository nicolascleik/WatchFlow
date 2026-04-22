package com.watchflow.WatchFlow.core.domain.midia;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class MediaBase {
    private UUID id;
    private Long tmdbId;
    private String titulo;
    private Integer anoDeLancamento;
    private String descricao;
    private double nota;
    private List<String> plataformasDisponiveis;
    private TipoMedia tipo;
}
