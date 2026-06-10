import { Link, useNavigate } from "@tanstack/react-router";
import { useState } from "react";
import { Search, LogOut, User as UserIcon, Film } from "lucide-react";
import { useSession } from "@/hooks/useSession";
import { store } from "@/lib/store";

export function Header() {
  const user = useSession();
  const navigate = useNavigate();
  const [q, setQ] = useState("");

  const onSearch = (e: React.FormEvent) => {
    e.preventDefault();
    if (q.trim()) navigate({ to: "/buscar", search: { q: q.trim() } });
  };

  return (
    <header className="sticky top-0 z-40 border-b border-border bg-background/85 backdrop-blur">
      <div className="mx-auto flex max-w-7xl items-center gap-4 px-4 py-3">
        <Link to="/" className="flex items-center gap-2 text-lg font-bold tracking-tight">
          <Film className="h-6 w-6 text-primary" />
          <span>Watch<span className="text-primary">Flow</span></span>
        </Link>
        <nav className="hidden gap-4 text-sm text-muted-foreground md:flex">
          <Link to="/" className="hover:text-foreground" activeOptions={{ exact: true }} activeProps={{ className: "text-foreground" }}>Início</Link>
          <Link to="/buscar" className="hover:text-foreground" activeProps={{ className: "text-foreground" }}>Buscar</Link>
          {user && <Link to="/perfil" className="hover:text-foreground" activeProps={{ className: "text-foreground" }}>Meu perfil</Link>}
        </nav>
        <form onSubmit={onSearch} className="ml-auto flex flex-1 max-w-md items-center gap-2 rounded-full border border-border bg-card px-3 py-1.5">
          <Search className="h-4 w-4 text-muted-foreground" />
          <input
            value={q}
            onChange={(e) => setQ(e.target.value)}
            placeholder="Buscar filmes e séries..."
            className="w-full bg-transparent text-sm outline-none placeholder:text-muted-foreground"
          />
        </form>
        {user ? (
          <div className="flex items-center gap-2">
            <Link to="/perfil" className="flex items-center gap-2 rounded-full bg-secondary px-3 py-1.5 text-sm hover:bg-muted">
              <UserIcon className="h-4 w-4" /> {user.nome.split(" ")[0]}
            </Link>
            <button onClick={() => store.logout()} className="rounded-full p-2 text-muted-foreground hover:text-foreground" aria-label="Sair">
              <LogOut className="h-4 w-4" />
            </button>
          </div>
        ) : (
          <Link to="/auth" className="rounded-full bg-primary px-4 py-1.5 text-sm font-semibold text-primary-foreground hover:opacity-90">
            Entrar
          </Link>
        )}
      </div>
    </header>
  );
}
