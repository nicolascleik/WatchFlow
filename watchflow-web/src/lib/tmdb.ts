// TMDB client — same API key used by the WatchFlow backend (academic project).
const TMDB_KEY = "f220526f102554d1c8ee5edcf2f68f9c";
const BASE = "https://api.themoviedb.org/3";
export const IMG = (path: string | null, size: "w200" | "w300" | "w500" | "original" = "w500") =>
  path ? `https://image.tmdb.org/t/p/${size}${path}` : "";

async function tmdb<T>(path: string, params: Record<string, string | number> = {}): Promise<T> {
  const url = new URL(`${BASE}${path}`);
  url.searchParams.set("api_key", TMDB_KEY);
  url.searchParams.set("language", "pt-BR");
  url.searchParams.set("watch_region", "BR");
  for (const [k, v] of Object.entries(params)) url.searchParams.set(k, String(v));
  const r = await fetch(url.toString());
  if (!r.ok) throw new Error(`TMDB ${r.status}`);
  return r.json();
}

export type Media = {
  id: number;
  title: string;
  overview: string;
  poster_path: string | null;
  backdrop_path: string | null;
  vote_average: number;
  release_date?: string;
  first_air_date?: string;
  genre_ids?: number[];
  type: "movie" | "tv";
};

const mapMovie = (m: any): Media => ({ ...m, title: m.title ?? m.name, type: "movie" });
const mapTv = (m: any): Media => ({ ...m, title: m.name ?? m.title, release_date: m.first_air_date, type: "tv" });

export const tmdbApi = {
  popularMovies: (page = 1) => tmdb<{ results: any[] }>("/movie/popular", { page }).then(r => r.results.map(mapMovie)),
  popularTv: (page = 1) => tmdb<{ results: any[] }>("/tv/popular", { page }).then(r => r.results.map(mapTv)),
  trending: () => tmdb<{ results: any[] }>("/trending/all/week").then(r =>
    r.results.map(x => x.media_type === "tv" ? mapTv(x) : mapMovie(x))
  ),
  searchMovies: (q: string, page = 1) => tmdb<{ results: any[] }>("/search/movie", { query: q, page }).then(r => r.results.map(mapMovie)),
  searchTv: (q: string, page = 1) => tmdb<{ results: any[] }>("/search/tv", { query: q, page }).then(r => r.results.map(mapTv)),
  multiSearch: async (q: string) => {
    const r = await tmdb<{ results: any[] }>("/search/multi", { query: q });
    return r.results
      .filter(x => x.media_type === "movie" || x.media_type === "tv")
      .map(x => x.media_type === "tv" ? mapTv(x) : mapMovie(x));
  },
  movieDetail: (id: number) => tmdb<any>(`/movie/${id}`, { append_to_response: "watch/providers,videos,credits,similar" }),
  tvDetail: (id: number) => tmdb<any>(`/tv/${id}`, { append_to_response: "watch/providers,videos,credits,similar" }),
  genresMovie: () => tmdb<{ genres: { id: number; name: string }[] }>("/genre/movie/list").then(r => r.genres),
  genresTv: () => tmdb<{ genres: { id: number; name: string }[] }>("/genre/tv/list").then(r => r.genres),
  discoverByGenre: (type: "movie" | "tv", genreId: number) =>
    tmdb<{ results: any[] }>(`/discover/${type}`, { with_genres: genreId, sort_by: "popularity.desc" })
      .then(r => r.results.map(type === "tv" ? mapTv : mapMovie)),
};

export type Provider = { provider_id: number; provider_name: string; logo_path: string };
export type ProvidersBR = {
  link?: string;
  flatrate?: Provider[]; // streaming
  rent?: Provider[];
  buy?: Provider[];
};
export const extractProvidersBR = (detail: any): ProvidersBR =>
  detail?.["watch/providers"]?.results?.BR ?? {};
