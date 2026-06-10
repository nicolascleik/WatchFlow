import { createFileRoute, Link } from "@tanstack/react-router";
import { useQuery } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { Check, Plus, Star } from "lucide-react";
import { tmdbApi, IMG, extractProvidersBR } from "@/lib/tmdb";
import { ProvidersBlock } from "@/components/ProvidersBlock";
import { MediaGrid } from "@/components/MediaCard";
import { store } from "@/lib/store";
import { useSession } from "@/hooks/useSession";

export const Route = createFileRoute("/filme/$id")({
  component: () => <DetailPage type="movie" />,
});

export function DetailPage({ type }: { type: "movie" | "tv" }) {
  const { id } = Route.useParams() as { id: string };
  const numId = Number(id);
  const q = useQuery({
    queryKey: [type, numId],
    queryFn: () => (type === "movie" ? tmdbApi.movieDetail(numId) : tmdbApi.tvDetail(numId)),
  });
  const user = useSession();
  const [assistido, setAssistido] = useState(false);
  useEffect(() => {
    if (user) setAssistido(store.listAssistidos(user.id).some(a => a.tmdbId === numId && a.tipo === type));
  }, [user, numId, type]);

  if (q.isLoading) return <div className="mx-auto max-w-7xl px-4 py-12 text-muted-foreground">Carregando...</div>;
  if (q.isError || !q.data) return <div className="mx-auto max-w-7xl px-4 py-12">Erro ao carregar.</div>;

  const d = q.data;
  const title = d.title ?? d.name;
  const year = (d.release_date ?? d.first_air_date ?? "").slice(0, 4);
  const providers = extractProvidersBR(d);
  const similares = (d.similar?.results ?? []).slice(0, 12).map((m: any) => ({
    ...m, title: m.title ?? m.name, type,
  }));

  const toggleAssistido = () => {
    if (!user) return alert("Entre para marcar como assistido.");
    if (assistido) { store.desmarcarAssistido(user.id, numId, type); setAssistido(false); }
    else { store.marcarAssistido(user.id, { tmdbId: numId, tipo: type, titulo: title, poster: d.poster_path, em: Date.now() }); setAssistido(true); }
  };

  return (
    <div>
      <div className="relative">
        {d.backdrop_path && (
          <>
            <img src={IMG(d.backdrop_path, "original")} alt={title} className="h-[42vh] w-full object-cover" />
            <div className="absolute inset-0 bg-gradient-to-t from-background to-background/30" />
          </>
        )}
      </div>
      <div className="mx-auto -mt-32 max-w-7xl px-4">
        <div className="grid gap-6 md:grid-cols-[220px_1fr]">
          <img src={IMG(d.poster_path, "w500")} alt={title} className="w-44 rounded-xl shadow-2xl md:w-full" />
          <div className="space-y-4">
            <div>
              <span className="rounded-full bg-primary/20 px-2 py-0.5 text-xs font-semibold uppercase text-primary">
                {type === "tv" ? "Série" : "Filme"}
              </span>
              <h1 className="mt-2 text-3xl font-bold sm:text-4xl">{title} <span className="text-muted-foreground">({year})</span></h1>
              <div className="mt-2 flex flex-wrap items-center gap-3 text-sm text-muted-foreground">
                <span className="flex items-center gap-1"><Star className="h-4 w-4 fill-primary text-primary" /> {d.vote_average?.toFixed(1)}</span>
                {d.genres?.map((g: any) => <span key={g.id} className="rounded-full bg-card px-2 py-0.5">{g.name}</span>)}
              </div>
            </div>
            <p className="max-w-3xl text-sm leading-relaxed text-foreground/90">{d.overview || "Sem sinopse disponível."}</p>
            <button onClick={toggleAssistido}
              className={`inline-flex items-center gap-2 rounded-full px-4 py-2 text-sm font-semibold ${assistido ? "bg-secondary text-foreground" : "bg-primary text-primary-foreground"}`}>
              {assistido ? <><Check className="h-4 w-4" /> Assistido</> : <><Plus className="h-4 w-4" /> Marcar como assistido</>}
            </button>
            {!user && (
              <p className="text-xs text-muted-foreground">
                <Link to="/auth" className="text-primary underline">Entre</Link> para registrar seu histórico.
              </p>
            )}
          </div>
        </div>

        <div className="mt-8">
          <ProvidersBlock providers={providers} />
        </div>

        {similares.length > 0 && (
          <section className="mt-12">
            <h2 className="mb-4 text-2xl font-bold">Similares</h2>
            <MediaGrid items={similares} />
          </section>
        )}
      </div>
      <div className="h-12" />
    </div>
  );
}
