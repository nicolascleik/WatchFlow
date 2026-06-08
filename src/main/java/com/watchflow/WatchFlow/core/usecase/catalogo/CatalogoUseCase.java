package com.watchflow.WatchFlow.core.usecase.catalogo;

import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import org.springframework.stereotype.Service;

import java.util.List;

// orquestra as buscas e repassa a paginação solicitada pelo front-end
@Service
public class CatalogoUseCase {

    private final TmdbGateway tmdbGateway;

    public CatalogoUseCase(TmdbGateway tmdbGateway) {
        this.tmdbGateway = tmdbGateway;
    }

    public List<Filme> buscarFilmes(String titulo, String idioma, int pagina) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("O título não pode estar vazio.");
        return tmdbGateway.buscarFilmes(titulo, idioma, pagina);
    }

    public List<Filme> buscarFilmesPopulares(String idioma, int pagina) {
        return tmdbGateway.buscarFilmesPopulares(idioma, pagina);
    }

    public Filme buscarDetalhesFilme(Long id, String idioma) {
        if (id == null) throw new IllegalArgumentException("ID inválido.");
        return tmdbGateway.buscarDetalhesFilme(id, idioma);
    }

    public List<Filme> descobrirFilmes(String genero, Integer ano, String idioma, int pagina) {
        return tmdbGateway.descobrirFilmes(genero, ano, idioma, pagina);
    }

    public List<Serie> buscarSeries(String titulo, String idioma, int pagina) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("O título não pode estar vazio.");
        return tmdbGateway.buscarSeries(titulo, idioma, pagina);
    }

    public List<Serie> buscarSeriesPopulares(String idioma, int pagina) {
        return tmdbGateway.buscarSeriesPopulares(idioma, pagina);
    }

    public Serie buscarDetalhesSerie(Long id, String idioma) {
        if (id == null) throw new IllegalArgumentException("ID inválido.");
        return tmdbGateway.buscarDetalhesSerie(id, idioma);
    }
}