import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { useEffect, useState } from "react";
import { Trash2, UserPlus, Check, Send } from "lucide-react";
import { store, type User, type Mensagem, type Assistido } from "@/lib/store";
import { useSession } from "@/hooks/useSession";

export const Route = createFileRoute("/perfil")({
  head: () => ({ meta: [{ title: "Meu perfil — WatchFlow" }] }),
  component: PerfilPage,
});

function PerfilPage() {
  const user = useSession();
  const navigate = useNavigate();
  useEffect(() => { if (user === null) navigate({ to: "/auth" }); }, [user, navigate]);
  const [tab, setTab] = useState<"assistidos" | "amigos" | "chat">("assistidos");
  if (!user) return null;

  return (
    <div className="mx-auto max-w-5xl px-4 py-8">
      <div className="rounded-2xl bg-card p-6">
        <h1 className="text-2xl font-bold">{user.nome}</h1>
        <p className="text-sm text-muted-foreground">{user.email}{user.cidade ? ` • ${user.cidade}/${user.estado}` : ""}</p>
      </div>

      <div className="mt-6 flex gap-2 border-b border-border">
        {(["assistidos", "amigos", "chat"] as const).map(t => (
          <button key={t} onClick={() => setTab(t)}
            className={`px-4 py-2 text-sm font-medium ${tab === t ? "border-b-2 border-primary text-foreground" : "text-muted-foreground"}`}>
            {t === "assistidos" ? "Assistidos" : t === "amigos" ? "Amigos" : "Chat"}
          </button>
        ))}
      </div>

      <div className="mt-6">
        {tab === "assistidos" && <Assistidos user={user} />}
        {tab === "amigos" && <Amigos user={user} />}
        {tab === "chat" && <Chat user={user} />}
      </div>
    </div>
  );
}

function Assistidos({ user }: { user: User }) {
  const [list, setList] = useState<Assistido[]>([]);
  useEffect(() => {
    const sync = () => setList(store.listAssistidos(user.id));
    sync();
    window.addEventListener("wf:assistidos", sync);
    return () => window.removeEventListener("wf:assistidos", sync);
  }, [user.id]);

  if (!list.length) return <p className="text-muted-foreground">Você ainda não marcou nada como assistido.</p>;
  return (
    <ul className="grid gap-3 sm:grid-cols-2">
      {list.map(a => (
        <li key={`${a.tipo}-${a.tmdbId}`} className="flex items-center gap-3 rounded-xl bg-card p-3">
          {a.poster && <img src={`https://image.tmdb.org/t/p/w200${a.poster}`} alt="" className="h-20 w-14 rounded object-cover" />}
          <div className="flex-1">
            <Link to={a.tipo === "tv" ? "/serie/$id" : "/filme/$id"} params={{ id: String(a.tmdbId) }} className="font-medium hover:text-primary">
              {a.titulo}
            </Link>
            <div className="text-xs text-muted-foreground">Marcado em {new Date(a.em).toLocaleDateString()}</div>
          </div>
          <button onClick={() => store.desmarcarAssistido(user.id, a.tmdbId, a.tipo)} className="text-muted-foreground hover:text-destructive">
            <Trash2 className="h-4 w-4" />
          </button>
        </li>
      ))}
    </ul>
  );
}

function Amigos({ user }: { user: User }) {
  const [, force] = useState(0);
  const refresh = () => force(n => n + 1);
  const all = store.listUsers().filter(u => u.id !== user.id);
  const amigos = store.amigosDe(user.id);
  const pendentes = store.pendentesPara(user.id);
  const amigoIds = new Set(amigos.map(a => a.id));
  const pendIds = new Set(store.listAmizades().filter(a => a.deId === user.id && a.status === "PENDENTE").map(a => a.paraId));

  return (
    <div className="space-y-6">
      {pendentes.length > 0 && (
        <div>
          <h3 className="mb-2 text-sm font-semibold uppercase text-muted-foreground">Solicitações recebidas</h3>
          <ul className="space-y-2">
            {pendentes.map(p => {
              const u = store.listUsers().find(x => x.id === p.deId);
              if (!u) return null;
              return (
                <li key={p.id} className="flex items-center justify-between rounded-xl bg-card p-3">
                  <span>{u.nome} <span className="text-xs text-muted-foreground">({u.email})</span></span>
                  <div className="flex gap-2">
                    <button onClick={() => { store.aceitarAmizade(p.id); refresh(); }} className="flex items-center gap-1 rounded-full bg-primary px-3 py-1 text-xs font-semibold text-primary-foreground">
                      <Check className="h-3 w-3" /> Aceitar
                    </button>
                    <button onClick={() => { store.removerAmizade(p.id); refresh(); }} className="rounded-full bg-secondary px-3 py-1 text-xs">Recusar</button>
                  </div>
                </li>
              );
            })}
          </ul>
        </div>
      )}

      <div>
        <h3 className="mb-2 text-sm font-semibold uppercase text-muted-foreground">Meus amigos</h3>
        {amigos.length === 0 ? <p className="text-sm text-muted-foreground">Você ainda não tem amigos.</p> : (
          <ul className="space-y-2">
            {amigos.map(a => (
              <li key={a.id} className="rounded-xl bg-card p-3">{a.nome} <span className="text-xs text-muted-foreground">({a.email})</span></li>
            ))}
          </ul>
        )}
      </div>

      <div>
        <h3 className="mb-2 text-sm font-semibold uppercase text-muted-foreground">Outros usuários</h3>
        {all.length === 0 ? <p className="text-sm text-muted-foreground">Nenhum outro usuário cadastrado ainda.</p> : (
          <ul className="space-y-2">
            {all.map(u => {
              const isAmigo = amigoIds.has(u.id);
              const isPend = pendIds.has(u.id);
              return (
                <li key={u.id} className="flex items-center justify-between rounded-xl bg-card p-3">
                  <span>{u.nome} <span className="text-xs text-muted-foreground">({u.cidade || "—"})</span></span>
                  {isAmigo ? <span className="text-xs text-muted-foreground">Amigo</span>
                    : isPend ? <span className="text-xs text-muted-foreground">Solicitado</span>
                    : <button onClick={() => { try { store.solicitarAmizade(user.id, u.id); refresh(); } catch (e: any) { alert(e.message); } }}
                        className="flex items-center gap-1 rounded-full bg-secondary px-3 py-1 text-xs hover:bg-muted">
                        <UserPlus className="h-3 w-3" /> Adicionar
                      </button>
                  }
                </li>
              );
            })}
          </ul>
        )}
      </div>
    </div>
  );
}

function Chat({ user }: { user: User }) {
  const amigos = store.amigosDe(user.id);
  const [sel, setSel] = useState<string | null>(amigos[0]?.id ?? null);
  const [msgs, setMsgs] = useState<Mensagem[]>([]);
  const [text, setText] = useState("");
  useEffect(() => {
    const sync = () => sel && setMsgs(store.conversa(user.id, sel));
    sync();
    window.addEventListener("wf:msg", sync);
    return () => window.removeEventListener("wf:msg", sync);
  }, [user.id, sel]);

  if (amigos.length === 0) return <p className="text-muted-foreground">Adicione amigos para conversar.</p>;

  return (
    <div className="grid gap-4 md:grid-cols-[200px_1fr]">
      <ul className="space-y-1">
        {amigos.map(a => (
          <li key={a.id}>
            <button onClick={() => setSel(a.id)} className={`w-full rounded-lg px-3 py-2 text-left text-sm ${sel === a.id ? "bg-primary text-primary-foreground" : "bg-card hover:bg-muted"}`}>
              {a.nome}
            </button>
          </li>
        ))}
      </ul>
      <div className="flex h-[60vh] flex-col rounded-xl bg-card">
        <div className="flex-1 space-y-2 overflow-y-auto p-4">
          {msgs.map(m => (
            <div key={m.id} className={`max-w-[75%] rounded-2xl px-3 py-2 text-sm ${m.deId === user.id ? "ml-auto bg-primary text-primary-foreground" : "bg-secondary"}`}>
              {m.conteudo}
            </div>
          ))}
          {msgs.length === 0 && <p className="text-sm text-muted-foreground">Sem mensagens ainda. Diga oi!</p>}
        </div>
        <form className="flex gap-2 border-t border-border p-3"
          onSubmit={(e) => { e.preventDefault(); if (!sel) return; try { store.enviarMensagem(user.id, sel, text); setText(""); } catch (err: any) { alert(err.message); } }}>
          <input value={text} onChange={(e) => setText(e.target.value)} placeholder="Digite uma mensagem..."
            className="flex-1 rounded-full border border-border bg-background px-4 py-2 outline-none focus:border-primary" />
          <button className="rounded-full bg-primary p-2 text-primary-foreground"><Send className="h-4 w-4" /></button>
        </form>
      </div>
    </div>
  );
}
