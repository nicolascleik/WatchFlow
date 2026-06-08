package com.watchflow.WatchFlow.adapters.out.gateway.MensagemGateway;

import com.watchflow.WatchFlow.core.domain.chat.Mensagem;
import com.watchflow.WatchFlow.core.gateway.MensagemGateway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class MensagemGatewayImpl implements MensagemGateway {

    private final CrudRepository<Mensagem, ?> mensagemRepository;

    public MensagemGatewayImpl(CrudRepository<Mensagem, ?> mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    @Override
    public Mensagem salvar(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }
}