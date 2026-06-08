package com.watchflow.WatchFlow.adapters.out.gateway;

import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MidiaEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.CategoriaJpaRepository;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.MidiaJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogoGatewayImpl implements CatalogoGateway {

    private final CategoriaJpaRepository categoriaRepository;
    private final MidiaJpaRepository mediaRepository;

    public CatalogoGatewayImpl(CategoriaJpaRepository categoriaRepository, MidiaJpaRepository mediaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(entity -> new Categoria(entity.getId(), entity.getTmdbGenreId(), entity.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public MidiaBase buscarOuSalvarMidiaLocal(MidiaBase media) {
        Optional<MidiaEntity> entityOptional = mediaRepository.findByTmdbId(media.getTmdbId());

        if (entityOptional.isPresent()) {
            media.setId(entityOptional.get().getId());
            return media;
        }

        MidiaEntity novaEntity = new MidiaEntity();
        novaEntity.setTmdbId(media.getTmdbId());
        novaEntity.setTipoMedia(media.getTipo().name());

        novaEntity = mediaRepository.save(novaEntity);
        media.setId(novaEntity.getId());

        return media;
    }

    @Override
    public List<MidiaBase> pesquisarPorTitulo(String titulo) {
        return List.of();
    }

    @Override
    public MidiaBase buscarDetalhesTmdb(Long tmdbId) {
        Filme filme = new Filme();
        filme.setTmdbId(tmdbId);
        return filme;
    }
}