package com.watchflow.watchflow.adapters.out.gateway.repository;

import com.watchflow.watchflow.core.domain.amizade.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, UUID> {
    
}