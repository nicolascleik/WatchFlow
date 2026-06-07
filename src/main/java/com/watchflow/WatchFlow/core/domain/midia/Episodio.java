package com.watchflow.WatchFlow.core.domain.midia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Episodio {
    private UUID id;
    private Integer numeroDaTemporadaDoEpisodio;
    private Integer numeroDoEpisodio; // refere ao numero do episodio atual, como 5
    private String tituloDoEpisodio;
    private Integer duracaoDoEpisodio;
    private String sinopseDoEpisodio;
    private Long episodioTMDBId;
}
