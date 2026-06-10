package com.watchflow.WatchFlow.infrastructure.persistence.gatewayimpl;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.core.gateway.UsuarioGateway;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.UsuarioEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.mapper.UsuarioPersistenceMapper;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioJpaRepository repository;

    @Override
    @Transactional
    public void salvar(Usuario usuario) {
        UsuarioEntity entity = UsuarioPersistenceMapper.toEntity(usuario);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void salvarTodos(List<Usuario> usuarios) {
        List<UsuarioEntity> entities = usuarios.stream()
                .map(UsuarioPersistenceMapper::toEntity)
                .collect(Collectors.toList());
        repository.saveAll(entities);
    }

    @Override
    public Usuario buscarPorId(UUID id) {
        return repository.findById(id)
                .map(UsuarioPersistenceMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(UsuarioPersistenceMapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll().stream()
                .map(UsuarioPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}