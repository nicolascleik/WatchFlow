import { IMG } from "@/lib/tmdb";
import type { ProvidersBR } from "@/lib/tmdb";

function Row({ label, list, link }: { label: string; list?: { provider_id: number; provider_name: string; logo_path: string }[]; link?: string }) {
  if (!list?.length) return null;
  return (
    <div>
      <div className="mb-2 text-xs font-semibold uppercase tracking-wider text-muted-foreground">{label}</div>
      <div className="flex flex-wrap gap-2">
        {list.map(p => (
          <a key={p.provider_id} href={link} target="_blank" rel="noreferrer"
             className="flex items-center gap-2 rounded-lg border border-border bg-card px-3 py-2 text-sm hover:border-primary">
            <img src={IMG(p.logo_path, "w200")} alt={p.provider_name} className="h-8 w-8 rounded-md" />
            <span>{p.provider_name}</span>
          </a>
        ))}
      </div>
    </div>
  );
}

export function ProvidersBlock({ providers }: { providers: ProvidersBR }) {
  const empty = !providers.flatrate?.length && !providers.rent?.length && !providers.buy?.length;
  return (
    <section className="space-y-4 rounded-xl border border-border bg-card/60 p-5">
      <h2 className="text-lg font-semibold">Onde assistir <span className="text-muted-foreground text-sm font-normal">(Brasil)</span></h2>
      {empty ? (
        <p className="text-sm text-muted-foreground">
          Ainda não há disponibilidade nas plataformas integradas. Pode ser um lançamento futuro ou conteúdo não distribuído no Brasil.
        </p>
      ) : (
        <div className="space-y-4">
          <Row label="Streaming (assinatura)" list={providers.flatrate} link={providers.link} />
          <Row label="Aluguel" list={providers.rent} link={providers.link} />
          <Row label="Compra" list={providers.buy} link={providers.link} />
        </div>
      )}
    </section>
  );
}
