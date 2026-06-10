package com.watchflow.WatchFlow.infrastructure.persistence.mapper;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.CategoriaEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.FilmeEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MidiaEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.SerieEntity;

public class MidiaPersistenceMapper {

    public static MidiaBase toDomain(MidiaEntity entity) {
        if (entity == null) return null;

        if (entity instanceof FilmeEntity filmeEntity) {
            return Filme.reconstruir(
                    filmeEntity.getId(),
                    filmeEntity.getTmdbId(),
                    filmeEntity.getTitulo(),
                    filmeEntity.getDescricao(),
                    filmeEntity.getAnoLancamento(),
                    filmeEntity.getNotaTmdb(),
                    filmeEntity.getPlataformasDisponiveis(),
                    filmeEntity.getDuracaoMinutos()
            );
        } else if (entity instanceof SerieEntity serieEntity) {
            return Serie.reconstruir(
                    serieEntity.getId(),
                    serieEntity.getTmdbId(),
                    serieEntity.getTitulo(),
                    serieEntity.getDescricao(),
                    serieEntity.getAnoLancamento(),
                    serieEntity.getNotaTmdb(),
                    serieEntity.getPlataformasDisponiveis(),
                    serieEntity.getTotalTemporadas()
            );
        }
        
        throw new IllegalArgumentException("Tipo de mídia desconhecido no mapeamento.");
    }

    public static MidiaEntity toEntity(MidiaBase domain) {
        if (domain == null) return null;

        MidiaEntity entity;

        if (domain instanceof Filme filme) {
            FilmeEntity filmeEntity = new FilmeEntity();
            filmeEntity.setDuracaoMinutos(filme.getDuracaoMinutos());
            entity = filmeEntity;
        } else if (domain instanceof Serie serie) {
            SerieEntity serieEntity = new SerieEntity();
            serieEntity.setTotalTemporadas(serie.getTotalTemporadas());
            entity = serieEntity;
        } else {
            throw new IllegalArgumentException("Tipo de mídia de domínio desconhecido.");
        }

        entity.setId(domain.getId());
        entity.setTmdbId(domain.getTmdbId());
        entity.setTitulo(domain.getTitulo());
        entity.setDescricao(domain.getDescricao());
        entity.setAnoLancamento(domain.getAnoLancamento());
        entity.setNotaTmdb(domain.getNotaTmdb());
        entity.setPlataformasDisponiveis(domain.getPlataformasDisponiveis());

        return entity;
    }

    public static Categoria toCategoriaDomain(CategoriaEntity entity) {
        if (entity == null) return null;
        return Categoria.reconstruir(entity.getId(), entity.getTmdbGenreId(), entity.getNome());
    }
}