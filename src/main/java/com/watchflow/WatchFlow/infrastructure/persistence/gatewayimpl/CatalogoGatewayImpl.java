package com.watchflow.WatchFlow.infrastructure.persistence.gatewayimpl;

import com.watchflow.WatchFlow.adapters.out.tmdb.TmdbAdapterMapper;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbFilmeResponse;
import com.watchflow.WatchFlow.adapters.out.tmdb.response.TmdbPesquisaResponse;
import com.watchflow.WatchFlow.core.domain.midia.Categoria;
import com.watchflow.WatchFlow.core.domain.midia.MidiaBase;
import com.watchflow.WatchFlow.core.gateway.CatalogoGateway;
import com.watchflow.WatchFlow.infrastructure.client.tmdb.TmdbFeignClient;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MidiaEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.mapper.MidiaPersistenceMapper;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.CategoriaJpaRepository;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.MidiaJpaRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogoGatewayImpl implements CatalogoGateway {

    private final TmdbFeignClient tmdbClient;
    private final MidiaJpaRepository midiaRepository;
    private final CategoriaJpaRepository categoriaRepository;

    @Override
    public List<MidiaBase> pesquisarPorTitulo(String titulo) {
        // Bate na API do TMDB buscando filmes e séries misturados
        TmdbPesquisaResponse response = tmdbClient.pesquisarMulti(titulo, "pt-BR", 1);
        
        if (response == null || response.results() == null) {
            return List.of();
        }

        // Filtra sujeiras (como pessoas que podem vir no /multi) e converte para domínio
        return response.results().stream()
                .filter(res -> res.title() != null || res.name() != null)
                .map(TmdbAdapterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public MidiaBase buscarDetalhesTmdb(Long tmdbId) {
        // Tenta buscar como Filme primeiro
        try {
            TmdbFilmeResponse filmeResponse = tmdbClient.buscarDetalhesFilme(tmdbId, "pt-BR");
            return TmdbAdapterMapper.toDomain(filmeResponse);
        } catch (FeignException.NotFound e) {
            // Se der 404, tenta buscar como Série
            try {
                TmdbFilmeResponse serieResponse = tmdbClient.buscarDetalhesSerie(tmdbId, "pt-BR");
                return TmdbAdapterMapper.toDomain(serieResponse);
            } catch (FeignException.NotFound ex) {
                return null; // Não existe no TMDB de jeito nenhum
            }
        }
    }

    @Override
    @Transactional
    public MidiaBase buscarOuSalvarMidiaLocal(MidiaBase midia) {
        // O cache local: Se já existe no nosso banco, não salvamos de novo
        Optional<MidiaEntity> entidadeExistente = midiaRepository.findByTmdbId(midia.getTmdbId());
        
        if (entidadeExistente.isPresent()) {
            return MidiaPersistenceMapper.toDomain(entidadeExistente.get());
        }

        // Se não existe, convertemos o domínio purista em Entidade JPA e persistimos
        MidiaEntity novaEntidade = MidiaPersistenceMapper.toEntity(midia);
        novaEntidade = midiaRepository.save(novaEntidade);
        
        return MidiaPersistenceMapper.toDomain(novaEntidade);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(MidiaPersistenceMapper::toCategoriaDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean todasCategoriasExistem(Set<UUID> categoriasIds) {
        if (categoriasIds == null || categoriasIds.isEmpty()) return true;
        
        long count = categoriaRepository.countByIdIn(categoriasIds);
        return count == categoriasIds.size();
    }
}