package com.watchflow.WatchFlow.infrastructure.persistence.repository;

import com.watchflow.WatchFlow.infrastructure.persistence.entity.MidiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MidiaJpaRepository extends JpaRepository<MidiaEntity, UUID> {
    Optional<MidiaEntity> findByTmdbId(Long tmdbId);
}