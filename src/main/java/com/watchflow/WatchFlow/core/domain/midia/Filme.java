package com.watchflow.WatchFlow.core.domain.midia;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class Filme extends MidiaBase {
    private Integer duracaoDoFilme;

    {
        this.setTipo(TipoMidia.FILME);
    }
}
