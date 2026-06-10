package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("SERIE")
@Getter
@Setter
public class SerieEntity extends MidiaEntity {

    @Column(name = "total_temporadas")
    private Integer totalTemporadas;
}