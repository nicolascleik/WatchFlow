package com.watchflow.WatchFlow.infrastructure.persistence.mapper;

import com.watchflow.WatchFlow.core.domain.usuario.Usuario;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.UsuarioEntity;

import java.util.HashSet;

public class UsuarioPersistenceMapper {

    public static UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setSenha(domain.getSenha());
        entity.setCidade(domain.getCidade());
        entity.setEstado(domain.getEstado());
        
        entity.setAmigosIds(new HashSet<>(domain.getAmigosIds()));
        entity.setCategoriasFavoritasIds(new HashSet<>(domain.getCategoriasFavoritasIds()));
        entity.setFilmesAssistidosIds(new HashSet<>(domain.getFilmesAssistidosIds()));
        entity.setEpisodiosAssistidosIds(new HashSet<>(domain.getEpisodiosAssistidosIds()));

        return entity;
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;

        return Usuario.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getAmigosIds(),
                entity.getCategoriasFavoritasIds(),
                entity.getFilmesAssistidosIds(),
                entity.getEpisodiosAssistidosIds()
        );
    }
}