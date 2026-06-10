import { createFileRoute, Link } from "@tanstack/react-router";
import { useQuery } from "@tanstack/react-query";
import { tmdbApi, IMG, type Media } from "@/lib/tmdb";
import { MediaGrid } from "@/components/MediaCard";
import { Play } from "lucide-react";

export const Route = createFileRoute("/")({
  head: () => ({
    meta: [
      { title: "WatchFlow — Descubra onde assistir" },
      { name: "description", content: "Encontre em qual streaming assistir filmes e séries. Inspirado no JustWatch." },
    ],
  }),
  component: Home,
});

function Home() {
  const trending = useQuery({ queryKey: ["trending"], queryFn: () => tmdbApi.trending() });
  const popMovies = useQuery({ queryKey: ["popular", "movies"], queryFn: () => tmdbApi.popularMovies() });
  const popTv = useQuery({ queryKey: ["popular", "tv"], queryFn: () => tmdbApi.popularTv() });

  const hero: Media | undefined = trending.data?.find(m => m.backdrop_path);

  return (
    <div>
      {hero && (
        <section className="relative h-[60vh] min-h-[420px] w-full overflow-hidden">
          <img src={IMG(hero.backdrop_path, "original")} alt={hero.title} className="absolute inset-0 h-full w-full object-cover" />
          <div className="absolute inset-0 bg-gradient-to-t from-background via-background/70 to-background/20" />
          <div className="relative z-10 mx-auto flex h-full max-w-7xl flex-col justify-end gap-4 px-4 pb-12">
            <span className="w-fit rounded-full bg-primary/20 px-3 py-1 text-xs font-semibold uppercase text-primary">Em alta esta semana</span>
            <h1 className="max-w-2xl text-4xl font-bold sm:text-5xl">{hero.title}</h1>
            <p className="max-w-2xl text-sm text-muted-foreground line-clamp-3">{hero.overview}</p>
            <Link
              to={hero.type === "tv" ? "/serie/$id" : "/filme/$id"}
              params={{ id: String(hero.id) }}
              className="flex w-fit items-center gap-2 rounded-full bg-primary px-5 py-2.5 text-sm font-semibold text-primary-foreground hover:opacity-90"
            >
              <Play className="h-4 w-4" /> Ver onde assistir
            </Link>
          </div>
        </section>
      )}

      <div className="mx-auto max-w-7xl space-y-10 px-4 py-10">
        <Section title="Em alta" loading={trending.isLoading} items={trending.data} />
        <Section title="Filmes populares" loading={popMovies.isLoading} items={popMovies.data} />
        <Section title="Séries populares" loading={popTv.isLoading} items={popTv.data} />
      </div>
    </div>
  );
}

function Section({ title, items, loading }: { title: string; items?: Media[]; loading?: boolean }) {
  return (
    <section>
      <h2 className="mb-4 text-2xl font-bold">{title}</h2>
      {loading ? (
        <div className="grid grid-cols-2 gap-3 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6">
          {Array.from({ length: 12 }).map((_, i) => <div key={i} className="aspect-[2/3] animate-pulse rounded-lg bg-card" />)}
        </div>
      ) : (
        <MediaGrid items={(items ?? []).slice(0, 18)} />
      )}
    </section>
  );
}
