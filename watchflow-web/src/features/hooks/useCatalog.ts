import { useQuery } from '@tanstack/react-query';
import { catalogService } from '../api/catalogService';

export const useFilmesPopulares = (idioma?: string, pagina?: number) => {
  return useQuery({
    queryKey: ['filmes-populares', idioma, pagina],
    queryFn: () => catalogService.getFilmesPopulares(idioma, pagina),
  });
};

export const useSeriesPopulares = (idioma?: string, pagina?: number) => {
  return useQuery({
    queryKey: ['series-populares', idioma, pagina],
    queryFn: () => catalogService.getSeriesPopulares(idioma, pagina),
  });
};