import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import {
  Outlet,
  Link,
  createRootRouteWithContext,
  HeadContent,
  Scripts,
} from "@tanstack/react-router";
import type { ReactNode } from "react";

import appCss from "../styles.css?url";
import { Header } from "@/components/Header";

function NotFoundComponent() {
  return (
    <div className="flex min-h-[60vh] items-center justify-center px-4">
      <div className="text-center">
        <h1 className="text-6xl font-bold">404</h1>
        <p className="mt-2 text-muted-foreground">Conteúdo não encontrado.</p>
        <Link to="/" className="mt-6 inline-flex rounded-full bg-primary px-4 py-2 text-sm font-semibold text-primary-foreground">
          Voltar ao início
        </Link>
      </div>
    </div>
  );
}

function ErrorComponent({ error, reset }: { error: Error; reset: () => void }) {
  return (
    <div className="flex min-h-[60vh] items-center justify-center px-4">
      <div className="max-w-md text-center">
        <h1 className="text-xl font-semibold">Algo deu errado</h1>
        <p className="mt-2 text-sm text-muted-foreground">{error.message}</p>
        <button onClick={reset} className="mt-6 rounded-full bg-primary px-4 py-2 text-sm font-semibold text-primary-foreground">
          Tentar novamente
        </button>
      </div>
    </div>
  );
}

export const Route = createRootRouteWithContext<{ queryClient: QueryClient }>()({
  head: () => ({
    meta: [
      { charSet: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      { title: "WatchFlow — Onde assistir filmes e séries" },
      { name: "description", content: "Descubra em qual streaming assistir seus filmes e séries favoritos, e conecte-se com outros cinéfilos." },
      { property: "og:title", content: "WatchFlow — Onde assistir filmes e séries" },
      { name: "twitter:title", content: "WatchFlow — Onde assistir filmes e séries" },
      { property: "og:description", content: "Descubra em qual streaming assistir seus filmes e séries favoritos, e conecte-se com outros cinéfilos." },
      { name: "twitter:description", content: "Descubra em qual streaming assistir seus filmes e séries favoritos, e conecte-se com outros cinéfilos." },
      { property: "og:image", content: "https://pub-bb2e103a32db4e198524a2e9ed8f35b4.r2.dev/fd7a822f-007b-43f9-9ce4-a4c2a45b085c/id-preview-cd4c0b26--9eef31de-95ee-4028-8e5f-2414c464e5cb.lovable.app-1781061051791.png" },
      { name: "twitter:image", content: "https://pub-bb2e103a32db4e198524a2e9ed8f35b4.r2.dev/fd7a822f-007b-43f9-9ce4-a4c2a45b085c/id-preview-cd4c0b26--9eef31de-95ee-4028-8e5f-2414c464e5cb.lovable.app-1781061051791.png" },
      { name: "twitter:card", content: "summary_large_image" },
      { property: "og:type", content: "website" },
    ],
    links: [{ rel: "stylesheet", href: appCss }],
  }),
  shellComponent: RootShell,
  component: RootComponent,
  notFoundComponent: NotFoundComponent,
  errorComponent: ErrorComponent,
});

function RootShell({ children }: { children: ReactNode }) {
  return (
    <html lang="pt-BR">
      <head><HeadContent /></head>
      <body>{children}<Scripts /></body>
    </html>
  );
}

function RootComponent() {
  const { queryClient } = Route.useRouteContext();
  return (
    <QueryClientProvider client={queryClient}>
      <div className="min-h-screen">
        <Header />
        <main><Outlet /></main>
        <footer className="border-t border-border py-8 text-center text-xs text-muted-foreground">
          WatchFlow • Projeto acadêmico • Dados fornecidos por TMDB
        </footer>
      </div>
    </QueryClientProvider>
  );
}
