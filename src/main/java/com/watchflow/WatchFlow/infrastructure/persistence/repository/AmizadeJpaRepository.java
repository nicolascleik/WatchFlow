package com.watchflow.WatchFlow.infrastructure.persistence.repository;

import com.watchflow.WatchFlow.core.domain.amizade.StatusAmizade;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.AmizadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AmizadeJpaRepository extends JpaRepository<AmizadeEntity, UUID> {

    // Busca os convites que chegaram para o usuário e ainda não foram respondidos
    List<AmizadeEntity> findBySolicitadoIdAndStatus(UUID solicitadoId, StatusAmizade status);

    // Consulta bidirecional: Verifica se já existe qualquer tipo de vínculo (Pendente, Aceita, etc) entre os dois
    @Query("SELECT COUNT(a) > 0 FROM AmizadeEntity a WHERE " +
           "(a.solicitanteId = :usuario1 AND a.solicitadoId = :usuario2) OR " +
           "(a.solicitanteId = :usuario2 AND a.solicitadoId = :usuario1)")
    boolean existeVinculo(@Param("usuario1") UUID usuario1, @Param("usuario2") UUID usuario2);

    // Consulta bidirecional: Retorna a entidade da amizade não importando quem enviou o convite
    @Query("SELECT a FROM AmizadeEntity a WHERE " +
           "(a.solicitanteId = :usuario1 AND a.solicitadoId = :usuario2) OR " +
           "(a.solicitanteId = :usuario2 AND a.solicitadoId = :usuario1)")
    Optional<AmizadeEntity> buscarPorUsuarios(@Param("usuario1") UUID usuario1, @Param("usuario2") UUID usuario2);
}