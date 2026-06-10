CREATE TABLE mensagens (
    id UUID PRIMARY KEY,
    remetente_id UUID NOT NULL,
    destinatario_id UUID NOT NULL,
    conteudo TEXT NOT NULL,
    data_envio TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_mensagem_remetente FOREIGN KEY (remetente_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_mensagem_destinatario FOREIGN KEY (destinatario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

-- Índices de Alta Performance para o Módulo de Chat
CREATE INDEX idx_mensagens_remetente_destinatario ON mensagens(remetente_id, destinatario_id);
CREATE INDEX idx_mensagens_data_envio ON mensagens(data_envio DESC);