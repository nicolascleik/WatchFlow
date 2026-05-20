package com.watchflow.WatchFlow.infrastructure.client.tmdb;

import com.watchflow.WatchFlow.adapters.out.gateway.TmdbGateway.TmdbGateway;
import com.watchflow.WatchFlow.core.domain.midia.Filme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbClientImpl implements TmdbGateway {

    @Override
    public List<Filme> buscarFilmePorTitulo(String titulo) {
        Filme filmeFalso = new Filme();
        filmeFalso.setTitulo(titulo + " (Teste Titulo)");
        filmeFalso.setDescricao("Teste Descrinção");
        filmeFalso.setDuracaoDoFilme(145);

        return List.of(filmeFalso);
    }
}