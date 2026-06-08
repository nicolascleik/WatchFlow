package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.midia.Filme;
import com.watchflow.WatchFlow.core.domain.midia.Serie;
import java.util.List;

public interface TmdbGateway {
    // filmes
    List<Filme> buscarFilmes(String titulo, String idioma, int pagina);
    List<Filme> buscarFilmesPopulares(String idioma, int pagina);
    Filme buscarDetalhesFilme(Long id, String idioma);
    List<Filme> descobrirFilmes(String genero, Integer ano, String idioma, int pagina);

    // series
    List<Serie> buscarSeries(String titulo, String idioma, int pagina);
    List<Serie> buscarSeriesPopulares(String idioma, int pagina);
    Serie buscarDetalhesSerie(Long id, String idioma);
}