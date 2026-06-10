package com.watchflow.WatchFlow.infrastructure.persistence.gatewayimpl;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.domain.chat.StatusMensagem;
import com.watchflow.WatchFlow.core.gateway.ChatGateway;
import com.watchflow.WatchFlow.infrastructure.persistence.entity.MensagemEntity;
import com.watchflow.WatchFlow.infrastructure.persistence.mapper.ChatPersistenceMapper;
import com.watchflow.WatchFlow.infrastructure.persistence.repository.MensagemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatGatewayImpl implements ChatGateway {

    private final MensagemJpaRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    @Transactional
    public void salvar(Mensagem mensagem) {
        MensagemEntity entity = ChatPersistenceMapper.toEntity(mensagem);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void salvarEmLote(List<Mensagem> mensagens) {
        List<MensagemEntity> entities = mensagens.stream()
                .map(ChatPersistenceMapper::toEntity)
                .collect(Collectors.toList());
        repository.saveAll(entities);
    }

    @Override
    public List<Mensagem> buscarHistorico(UUID usuario1, UUID usuario2, int limite, int pagina) {
        // Converte a paginação do Domínio para o Pageable do Spring Data
        // Nota: as páginas no Spring começam em 0. Se o comando mandar página 1, ajustamos para 0.
        int paginaReal = Math.max(0, pagina - 1);
        Pageable pageable = PageRequest.of(paginaReal, limite);

        return repository.buscarHistorico(usuario1, usuario2, pageable).stream()
                .map(ChatPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Mensagem> buscarMensagensNaoLidas(UUID remetenteId, UUID destinatarioId) {
        return repository.findByRemetenteIdAndDestinatarioIdAndStatusNot(
                remetenteId, destinatarioId, StatusMensagem.LIDA
        ).stream()
                .map(ChatPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void despacharEventoTempoReal(Mensagem mensagem) {
        // Envia a mensagem apenas para o túnel privado do destinatário.
        // O Spring vai montar a fila automaticamente no formato: /user/{destinatarioId}/queue/mensagens
        messagingTemplate.convertAndSendToUser(
                mensagem.getDestinatarioId().toString(),
                "/queue/mensagens",
                mensagem // O Jackson se encarrega de converter a Entidade Pura em JSON para a rede
        );
    }
}