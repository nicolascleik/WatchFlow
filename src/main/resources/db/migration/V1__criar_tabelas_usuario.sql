CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cidade VARCHAR(255),
    estado VARCHAR(2)
);

CREATE TABLE usuario_amigos (
    usuario_id UUID NOT NULL,
    amigo_id UUID NOT NULL,
    PRIMARY KEY (usuario_id, amigo_id),
    CONSTRAINT fk_usuario_amigo FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

CREATE TABLE usuario_categorias (
    usuario_id UUID NOT NULL,
    categoria_id UUID NOT NULL,
    PRIMARY KEY (usuario_id, categoria_id),
    CONSTRAINT fk_usuario_categoria FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

CREATE TABLE usuario_filmes (
    usuario_id UUID NOT NULL,
    filme_id UUID NOT NULL,
    PRIMARY KEY (usuario_id, filme_id),
    CONSTRAINT fk_usuario_filme FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

CREATE TABLE usuario_episodios (
    usuario_id UUID NOT NULL,
    episodio_id UUID NOT NULL,
    PRIMARY KEY (usuario_id, episodio_id),
    CONSTRAINT fk_usuario_episodio FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);