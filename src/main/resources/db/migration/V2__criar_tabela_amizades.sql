CREATE TABLE amizades (
    id UUID PRIMARY KEY,
    solicitante_id UUID NOT NULL,
    solicitado_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    data_solicitacao TIMESTAMP NOT NULL,
    CONSTRAINT fk_amizade_solicitante FOREIGN KEY (solicitante_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_amizade_solicitado FOREIGN KEY (solicitado_id) REFERENCES usuarios (id) ON DELETE CASCADE
);