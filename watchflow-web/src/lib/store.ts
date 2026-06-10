// Local "backend": users, session, friends, messages, watched list.
// Mirrors the WatchFlow domain (Usuario, Amizade, Mensagem, MidiaAssistida) using localStorage.
export type User = {
  id: string;
  nome: string;
  email: string;
  senha: string; // demo only
  cidade?: string;
  estado?: string;
  generosFavoritos: number[];
};
export type Amizade = { id: string; deId: string; paraId: string; status: "PENDENTE" | "ACEITA" };
export type Mensagem = { id: string; deId: string; paraId: string; conteudo: string; criadaEm: number };
export type Assistido = { tmdbId: number; tipo: "movie" | "tv"; titulo: string; poster: string | null; em: number };

const K = {
  users: "wf.users",
  session: "wf.session",
  amizades: "wf.amizades",
  mensagens: "wf.mensagens",
  assistidos: (uid: string) => `wf.assistidos.${uid}`,
};

const read = <T,>(k: string, def: T): T => {
  if (typeof window === "undefined") return def;
  try { return JSON.parse(localStorage.getItem(k) ?? "") as T; } catch { return def; }
};
const write = (k: string, v: unknown) => { if (typeof window !== "undefined") localStorage.setItem(k, JSON.stringify(v)); };
const uid = () => {
  if (typeof crypto !== "undefined" && typeof crypto.randomUUID === "function") {
    return crypto.randomUUID();
  }
  return `wf-${Date.now().toString(36)}-${Math.random().toString(36).slice(2)}`;
};

export const store = {
  // ----- usuario -----
  listUsers: () => read<User[]>(K.users, []),
  register(data: Omit<User, "id">): User {
    const users = store.listUsers();
    if (users.some(u => u.email.toLowerCase() === data.email.toLowerCase())) {
      throw new Error("Já existe uma conta com esse e-mail.");
    }
    const u: User = { ...data, id: uid() };
    write(K.users, [...users, u]);
    return u;
  },
  login(email: string, senha: string): User {
    const u = store.listUsers().find(x => x.email.toLowerCase() === email.toLowerCase() && x.senha === senha);
    if (!u) throw new Error("E-mail ou senha incorretos.");
    write(K.session, u.id);
    window.dispatchEvent(new Event("wf:session"));
    return u;
  },
  logout() { if (typeof window !== "undefined") { localStorage.removeItem(K.session); window.dispatchEvent(new Event("wf:session")); } },
  current(): User | null {
    const id = typeof window !== "undefined" ? read<string | null>(K.session, null) : null;
    if (!id) return null;
    return store.listUsers().find(u => u.id === id) ?? null;
  },
  updateUser(patch: Partial<User>) {
    const cur = store.current(); if (!cur) return;
    const users = store.listUsers().map(u => u.id === cur.id ? { ...u, ...patch } : u);
    write(K.users, users);
    window.dispatchEvent(new Event("wf:session"));
  },

  // ----- amizade -----
  listAmizades: () => read<Amizade[]>(K.amizades, []),
  solicitarAmizade(deId: string, paraId: string) {
    if (deId === paraId) throw new Error("Não pode adicionar a si mesmo.");
    const list = store.listAmizades();
    if (list.some(a => (a.deId === deId && a.paraId === paraId) || (a.deId === paraId && a.paraId === deId))) {
      throw new Error("Já existe uma solicitação ou amizade.");
    }
    write(K.amizades, [...list, { id: uid(), deId, paraId, status: "PENDENTE" }]);
  },
  aceitarAmizade(id: string) {
    write(K.amizades, store.listAmizades().map(a => a.id === id ? { ...a, status: "ACEITA" } : a));
  },
  removerAmizade(id: string) {
    write(K.amizades, store.listAmizades().filter(a => a.id !== id));
  },
  amigosDe(uidv: string): User[] {
    const ids = store.listAmizades()
      .filter(a => a.status === "ACEITA" && (a.deId === uidv || a.paraId === uidv))
      .map(a => a.deId === uidv ? a.paraId : a.deId);
    return store.listUsers().filter(u => ids.includes(u.id));
  },
  pendentesPara(uidv: string) {
    return store.listAmizades().filter(a => a.status === "PENDENTE" && a.paraId === uidv);
  },

  // ----- mensagens -----
  listMensagens: () => read<Mensagem[]>(K.mensagens, []),
  enviarMensagem(deId: string, paraId: string, conteudo: string) {
    if (!conteudo.trim()) throw new Error("Mensagem vazia.");
    const m: Mensagem = { id: uid(), deId, paraId, conteudo: conteudo.trim(), criadaEm: Date.now() };
    write(K.mensagens, [...store.listMensagens(), m]);
    window.dispatchEvent(new Event("wf:msg"));
    return m;
  },
  conversa(a: string, b: string) {
    return store.listMensagens()
      .filter(m => (m.deId === a && m.paraId === b) || (m.deId === b && m.paraId === a))
      .sort((x, y) => x.criadaEm - y.criadaEm);
  },

  // ----- assistidos -----
  listAssistidos(uidv: string): Assistido[] { return read<Assistido[]>(K.assistidos(uidv), []); },
  marcarAssistido(uidv: string, a: Assistido) {
    const list = store.listAssistidos(uidv).filter(x => !(x.tmdbId === a.tmdbId && x.tipo === a.tipo));
    write(K.assistidos(uidv), [{ ...a, em: Date.now() }, ...list]);
    window.dispatchEvent(new Event("wf:assistidos"));
  },
  desmarcarAssistido(uidv: string, tmdbId: number, tipo: "movie" | "tv") {
    write(K.assistidos(uidv), store.listAssistidos(uidv).filter(x => !(x.tmdbId === tmdbId && x.tipo === tipo)));
    window.dispatchEvent(new Event("wf:assistidos"));
  },
};
