package com.watchflow.WatchFlow.core.gateway;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;

import java.util.List;
import java.util.UUID;

public interface ChatGateway {
    
    // Contratos de Persistência
    void salvar(Mensagem mensagem);
    void salvarEmLote(List<Mensagem> mensagens);
    List<Mensagem> buscarHistorico(UUID usuario1, UUID usuario2, int limite, int pagina);
    List<Mensagem> buscarMensagensNaoLidas(UUID remetenteId, UUID destinatarioId);
    
    // Contrato de Tempo Real (WebSocket)
    void despacharEventoTempoReal(Mensagem mensagem);
}