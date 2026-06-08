export interface MidiaBase {
  id?: string;
  tmdbId: number;
  titulo: string;
  sinopse: string;
  posterPath: string;
  backdropPath: string;
  dataLancamento: string;
  notaMedia: number;
  idiomaOriginal: string;
  tipo: 'FILME' | 'SERIE';
}

export interface Filme extends MidiaBase {
}

export interface Serie extends MidiaBase {
  quantidadeTemporadas?: number;
  quantidadeEpisodios?: number;
}