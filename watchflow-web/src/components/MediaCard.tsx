import { Link } from "@tanstack/react-router";
import { Star } from "lucide-react";
import { IMG, type Media } from "@/lib/tmdb";

export function MediaCard({ media }: { media: Media }) {
  const to = media.type === "tv" ? "/serie/$id" : "/filme/$id";
  const year = (media.release_date || media.first_air_date || "").slice(0, 4);
  return (
    <Link
      to={to}
      params={{ id: String(media.id) }}
      className="group block overflow-hidden rounded-lg bg-card transition hover:-translate-y-1 hover:shadow-xl hover:shadow-primary/10"
    >
      <div className="relative aspect-[2/3] w-full overflow-hidden bg-muted">
        {media.poster_path ? (
          <img src={IMG(media.poster_path, "w300")} alt={media.title} loading="lazy" className="h-full w-full object-cover transition group-hover:scale-105" />
        ) : (
          <div className="flex h-full items-center justify-center text-xs text-muted-foreground">Sem imagem</div>
        )}
        <div className="absolute left-2 top-2 flex items-center gap-1 rounded-full bg-background/80 px-2 py-0.5 text-xs font-semibold">
          <Star className="h-3 w-3 fill-primary text-primary" /> {media.vote_average?.toFixed(1)}
        </div>
        <span className="absolute right-2 top-2 rounded-full bg-primary/90 px-2 py-0.5 text-[10px] font-bold uppercase text-primary-foreground">
          {media.type === "tv" ? "Série" : "Filme"}
        </span>
      </div>
      <div className="p-2.5">
        <div className="line-clamp-1 text-sm font-medium">{media.title}</div>
        <div className="text-xs text-muted-foreground">{year || "—"}</div>
      </div>
    </Link>
  );
}

export function MediaGrid({ items }: { items: Media[] }) {
  return (
    <div className="grid grid-cols-2 gap-3 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6">
      {items.map((m) => <MediaCard key={`${m.type}-${m.id}`} media={m} />)}
    </div>
  );
}
