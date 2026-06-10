package com.watchflow.WatchFlow.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("FILME") // O Hibernate injeta essa string na coluna tipo_midia
@Getter
@Setter
public class FilmeEntity extends MidiaEntity {

    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;
}