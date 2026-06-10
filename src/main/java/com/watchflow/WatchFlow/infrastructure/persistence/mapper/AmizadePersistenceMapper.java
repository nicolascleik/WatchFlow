package com.watchflow.WatchFlow.infrastructure.persistence.mapper;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.AmizadeEntity;

public class AmizadePersistenceMapper {

    public static AmizadeEntity toEntity(Amizade domain) {
        if (domain == null) return null;

        AmizadeEntity entity = new AmizadeEntity();
        entity.setId(domain.getId());
        entity.setSolicitanteId(domain.getSolicitanteId());
        entity.setSolicitadoId(domain.getSolicitadoId());
        entity.setStatus(domain.getStatus());
        entity.setDataSolicitacao(domain.getDataSolicitacao());

        return entity;
    }

    public static Amizade toDomain(AmizadeEntity entity) {
        if (entity == null) return null;

        return Amizade.reconstruir(
                entity.getId(),
                entity.getSolicitanteId(),
                entity.getSolicitadoId(),
                entity.getStatus(),
                entity.getDataSolicitacao()
        );
    }
}