import axios from 'axios';
import { Filme, Serie } from '../types';

const api = axios.create({
  baseURL: 'http://localhost:8080', 
});

export const catalogService = {
  getFilmesPopulares: async (idioma = 'pt-BR', pagina = 1): Promise<Filme[]> => {
    const { data } = await api.get('/catalogo/filmes/populares', {
      params: { idioma, pagina },
    });
    return data;
  },

  getSeriesPopulares: async (idioma = 'pt-BR', pagina = 1): Promise<Serie[]> => {
    const { data } = await api.get('/catalogo/series/buscar', {
      params: { titulo: 'a', idioma, pagina },
    });
    return data;
  },
};