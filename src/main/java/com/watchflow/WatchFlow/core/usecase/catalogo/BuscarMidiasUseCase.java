package com.watchflow.watchflow.core.usecase.catalogo;

import java.util.List;

import com.watchflow.watchflow.adapters.out.gateway.MidiaGateway;
import com.watchflow.watchflow.core.domain.midia.MediaBase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarMidiasUseCase {

    private final MidiaGateway midiaGateway;

    
    public List<MediaBase> executarBuscaPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título de busca não pode estar vazio.");
        }
        return midiaGateway.buscarPorTitulo(titulo.trim());
    }

   
    public List<MediaBase> executarBuscaOrganizada(CriterioOrdenacao criterio) {
        if (criterio == null) {
            throw new IllegalArgumentException("O critério de ordenação deve ser informado.");
        }
        return midiaGateway.buscarTodasOrdenadas(criterio);
    }

    public enum CriterioOrdenacao {
        MAIS_NOVO,
        MAIS_VELHO,
        ORDEM_ALFABETICA,
        DURACAO_MAIOR,
        DURACAO_MENOR
    }
}