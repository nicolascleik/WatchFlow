import { useFilmesPopulares } from '../hooks/useCatalog';
import { MovieCard } from '../components/MovieCard';

export const CatalogHome = () => {
  const { data: filmes, isLoading, isError } = useFilmesPopulares();

  if (isLoading) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-black text-white">
        <p className="animate-pulse text-xl">Carregando catálogo...</p>
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-black text-white">
        <p className="text-red-500">Erro ao carregar filmes. O backend está rodando?</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-black pb-10 text-white">
      {/* Hero Section Placeholder */}
      <section className="relative flex h-[50vh] w-full items-end bg-zinc-800 p-8 md:h-[70vh]">
        <div className="absolute inset-0 bg-gradient-to-t from-black via-black/50 to-transparent" />
        <div className="relative z-10 max-w-2xl">
          <h1 className="text-4xl font-extrabold md:text-6xl">Em Alta no WatchFlow</h1>
          <p className="mt-4 text-lg text-gray-300">
            Descubra os filmes mais assistidos e bem avaliados pela comunidade.
          </p>
        </div>
      </section>

      {/* Seção de Filmes Populares */}
      <section className="px-4 pt-10 md:px-8">
        <div className="mb-6 flex items-center justify-between">
          <h2 className="text-2xl font-bold">Filmes Populares</h2>
        </div>

        <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 2xl:grid-cols-8">
          {filmes?.map((filme) => (
            <MovieCard key={filme.tmdbId} midia={filme} />
          ))}
        </div>
      </section>
    </div>
  );
};