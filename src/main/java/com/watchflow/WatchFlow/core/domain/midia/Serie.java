package com.watchflow.WatchFlow.core.domain.midia;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class Serie extends MediaBase {
    private Integer totalDeTemporada;

    @Builder.Default
    private List<Episodio> listaDeEpisodio = new ArrayList<>();

    {
        this.setTipo(TipoMedia.SERIE);
    }
}
