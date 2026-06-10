package com.watchflow.WatchFlow.infrastructure.persistence.mapper;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MensagemEntity;

public class ChatPersistenceMapper {

    public static Mensagem toDomain(MensagemEntity entity) {
        if (entity == null) return null;

        return Mensagem.reconstruir(
                entity.getId(),
                entity.getRemetenteId(),
                entity.getDestinatarioId(),
                entity.getConteudo(),
                entity.getDataEnvio(),
                entity.getStatus()
        );
    }

    public static MensagemEntity toEntity(Mensagem domain) {
        if (domain == null) return null;

        MensagemEntity entity = new MensagemEntity();
        entity.setId(domain.getId());
        entity.setRemetenteId(domain.getRemetenteId());
        entity.setDestinatarioId(domain.getDestinatarioId());
        entity.setConteudo(domain.getConteudo());
        entity.setDataEnvio(domain.getDataEnvio());
        entity.setStatus(domain.getStatus());

        return entity;
    }
}