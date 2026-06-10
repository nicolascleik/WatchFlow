-- Criação da tabela de categorias (Gêneros do TMDB)
CREATE TABLE categorias (
    id UUID PRIMARY KEY,
    tmdb_genre_id INTEGER NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL
);

-- Tabela unificada para Filmes e Séries (Single Table Strategy)
CREATE TABLE midias (
    id UUID PRIMARY KEY,
    tipo_midia VARCHAR(31) NOT NULL, -- Coluna gerada pelo @DiscriminatorColumn
    tmdb_id BIGINT NOT NULL UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    ano_lancamento INTEGER,
    nota_tmdb DOUBLE PRECISION,
    duracao_minutos INTEGER, -- Exclusivo de Filme
    total_temporadas INTEGER -- Exclusivo de Série
);

-- Tabela auxiliar gerada pelo @ElementCollection para a lista de plataformas
CREATE TABLE midia_plataformas (
    midia_id UUID NOT NULL,
    plataforma VARCHAR(255) NOT NULL,
    CONSTRAINT fk_midia_plataformas FOREIGN KEY (midia_id) REFERENCES midias (id) ON DELETE CASCADE
);

-- Tabela de episódios vinculada à Série (Mídia)
CREATE TABLE episodios (
    id UUID PRIMARY KEY,
    serie_id UUID NOT NULL,
    temporada INTEGER NOT NULL,
    numero INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    CONSTRAINT fk_episodio_serie FOREIGN KEY (serie_id) REFERENCES midias (id) ON DELETE CASCADE
);