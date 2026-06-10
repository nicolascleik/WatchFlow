import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { useState } from "react";
import { store } from "@/lib/store";

export const Route = createFileRoute("/auth")({
  head: () => ({ meta: [{ title: "Entrar — WatchFlow" }] }),
  component: AuthPage,
});

function AuthPage() {
  const [mode, setMode] = useState<"login" | "register">("login");
  const [form, setForm] = useState({ nome: "", email: "", senha: "", cidade: "", estado: "" });
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const submit = (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    try {
      if (mode === "register") {
        store.register({ nome: form.nome, email: form.email, senha: form.senha, cidade: form.cidade, estado: form.estado, generosFavoritos: [] });
      }
      store.login(form.email, form.senha);
      navigate({ to: "/perfil" });
    } catch (err: any) {
      setError(err.message);
    }
  };

  return (
    <div className="mx-auto flex min-h-[80vh] max-w-md flex-col justify-center px-4 py-10">
      <h1 className="text-3xl font-bold">{mode === "login" ? "Entrar" : "Criar conta"}</h1>
      <p className="mt-1 text-sm text-muted-foreground">
        {mode === "login" ? "Acesse sua conta WatchFlow." : "Cadastre-se para registrar seu histórico e fazer amigos."}
      </p>

      <form onSubmit={submit} className="mt-6 space-y-3">
        {mode === "register" && (
          <Input label="Nome" value={form.nome} onChange={(v) => setForm({ ...form, nome: v })} required />
        )}
        <Input label="E-mail" type="email" value={form.email} onChange={(v) => setForm({ ...form, email: v })} required />
        <Input label="Senha" type="password" value={form.senha} onChange={(v) => setForm({ ...form, senha: v })} required />
        {mode === "register" && (
          <div className="grid grid-cols-2 gap-3">
            <Input label="Cidade" value={form.cidade} onChange={(v) => setForm({ ...form, cidade: v })} />
            <Input label="Estado" value={form.estado} onChange={(v) => setForm({ ...form, estado: v })} />
          </div>
        )}
        {error && <p className="text-sm text-destructive">{error}</p>}
        <button className="w-full rounded-full bg-primary py-2.5 text-sm font-semibold text-primary-foreground">
          {mode === "login" ? "Entrar" : "Cadastrar"}
        </button>
      </form>

      <button
        onClick={() => { setMode(mode === "login" ? "register" : "login"); setError(null); }}
        className="mt-4 text-sm text-muted-foreground hover:text-foreground"
      >
        {mode === "login" ? "Não tem conta? Cadastre-se" : "Já tem conta? Entrar"}
      </button>
    </div>
  );
}

function Input({ label, value, onChange, type = "text", required }: { label: string; value: string; onChange: (v: string) => void; type?: string; required?: boolean }) {
  return (
    <label className="block">
      <span className="mb-1 block text-xs font-medium text-muted-foreground">{label}</span>
      <input
        type={type}
        value={value}
        required={required}
        onChange={(e) => onChange(e.target.value)}
        className="w-full rounded-lg border border-border bg-card px-3 py-2 outline-none focus:border-primary"
      />
    </label>
  );
}
