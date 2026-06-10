package com.watchflow.WatchFlow.infrastructure.persistence.repository;

import com.watchflow.WatchFlow.core.domain.chat.StatusMensagem;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MensagemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MensagemJpaRepository extends JpaRepository<MensagemEntity, UUID> {

    // Busca o histórico bidirecional ordenado da mensagem mais recente para a mais antiga
    @Query("SELECT m FROM MensagemEntity m WHERE " +
           "(m.remetenteId = :usuario1 AND m.destinatarioId = :usuario2) OR " +
           "(m.remetenteId = :usuario2 AND m.destinatarioId = :usuario1) " +
           "ORDER BY m.dataEnvio DESC")
    List<MensagemEntity> buscarHistorico(
            @Param("usuario1") UUID usuario1, 
            @Param("usuario2") UUID usuario2, 
            Pageable pageable
    );

    // Busca rápida para a rotina de "marcar como lida" (Dois checks azuis)
    List<MensagemEntity> findByRemetenteIdAndDestinatarioIdAndStatusNot(
            UUID remetenteId, 
            UUID destinatarioId, 
            StatusMensagem status
    );
}