package com.watchflow.WatchFlow.adapters.out.gateway.repository;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
    
}