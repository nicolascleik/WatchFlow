import { createFileRoute } from "@tanstack/react-router";
import { useQuery } from "@tanstack/react-query";
import { z } from "zod";
import { tmdbApi } from "@/lib/tmdb";
import { MediaGrid } from "@/components/MediaCard";
import { useState } from "react";

const search = z.object({ q: z.string().optional() });

export const Route = createFileRoute("/buscar")({
  validateSearch: search,
  head: () => ({ meta: [{ title: "Buscar — WatchFlow" }] }),
  component: BuscarPage,
});

function BuscarPage() {
  const { q } = Route.useSearch();
  const navigate = Route.useNavigate();
  const [input, setInput] = useState(q ?? "");
  const [tipo, setTipo] = useState<"all" | "movie" | "tv">("all");

  const query = useQuery({
    queryKey: ["search", q, tipo],
    queryFn: async () => {
      if (!q) return [];
      if (tipo === "movie") return tmdbApi.searchMovies(q);
      if (tipo === "tv") return tmdbApi.searchTv(q);
      return tmdbApi.multiSearch(q);
    },
    enabled: !!q,
  });

  return (
    <div className="mx-auto max-w-7xl px-4 py-8">
      <h1 className="text-3xl font-bold">Buscar</h1>
      <form
        className="mt-4 flex gap-2"
        onSubmit={(e) => { e.preventDefault(); navigate({ search: { q: input.trim() || undefined } }); }}
      >
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ex.: Interestelar, Breaking Bad..."
          className="w-full rounded-full border border-border bg-card px-4 py-2 outline-none focus:border-primary"
        />
        <button className="rounded-full bg-primary px-5 py-2 text-sm font-semibold text-primary-foreground">Buscar</button>
      </form>

      <div className="mt-4 flex gap-2 text-sm">
        {(["all", "movie", "tv"] as const).map(t => (
          <button key={t} onClick={() => setTipo(t)}
            className={`rounded-full px-3 py-1 ${tipo === t ? "bg-primary text-primary-foreground" : "bg-card text-muted-foreground hover:text-foreground"}`}>
            {t === "all" ? "Tudo" : t === "movie" ? "Filmes" : "Séries"}
          </button>
        ))}
      </div>

      <div className="mt-8">
        {!q && <p className="text-muted-foreground">Digite o nome de um filme ou série para começar.</p>}
        {query.isLoading && <p className="text-muted-foreground">Buscando...</p>}
        {query.data && query.data.length === 0 && <p className="text-muted-foreground">Nenhum resultado encontrado para "{q}".</p>}
        {query.data && query.data.length > 0 && <MediaGrid items={query.data} />}
      </div>
    </div>
  );
}
