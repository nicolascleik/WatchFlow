package com.watchflow.WatchFlow.adapters.out.repository;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, UUID> {
    
}