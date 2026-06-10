package com.watchflow.WatchFlow.infrastructure.persistence.gatewayimpl;

import com.watchflow.WatchFlow.core.domain.amizade.Amizade;
import com.watchflow.WatchFlow.core.domain.amizade.StatusAmizade;
import com.watchflow.WatchFlow.core.gateway.AmizadeGateway;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.AmizadeEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.mapper.AmizadePersistenceMapper;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.AmizadeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmizadeGatewayImpl implements AmizadeGateway {

    private final AmizadeJpaRepository repository;

    @Override
    @Transactional
    public void salvar(Amizade amizade) {
        AmizadeEntity entity = AmizadePersistenceMapper.toEntity(amizade);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void excluir(Amizade amizade) {
        AmizadeEntity entity = AmizadePersistenceMapper.toEntity(amizade);
        repository.delete(entity);
    }

    @Override
    public Amizade buscarPorId(UUID id) {
        return repository.findById(id)
                .map(AmizadePersistenceMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Amizade> buscarPendentes(UUID usuarioSolicitadoId) {
        return repository.findBySolicitadoIdAndStatus(usuarioSolicitadoId, StatusAmizade.PENDENTE)
                .stream()
                .map(AmizadePersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeAmizade(UUID usuario1, UUID usuario2) {
        return repository.existeVinculo(usuario1, usuario2);
    }

    @Override
    public Amizade buscarPorUsuarios(UUID usuario1, UUID usuario2) {
        return repository.buscarPorUsuarios(usuario1, usuario2)
                .map(AmizadePersistenceMapper::toDomain)
                .orElse(null);
    }
}