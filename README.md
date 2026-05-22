# WatchFlow - Streaming Locator & Social Network

## Sobre o Projeto

O **WatchFlow** é uma plataforma híbrida desenhada para resolver a atual fragmentação do mercado de streaming, unindo a utilidade de um localizador de mídias com o engajamento de uma rede social nichada.

O sistema permite pesquisar filmes e séries e descobrir instantaneamente em qual plataforma de streaming o título está disponível para assinatura ou aluguel. Além de ser um utilitário, o WatchFlow conecta cinéfilos. Através de um sistema inteligente de *match* baseado em categorias favoritas (animação, comédia, terror, etc.) e geolocalização, a plataforma sugere conexões reais — permitindo encontrar amigos com gostos semelhantes na sua região.

---

## Principais Funcionalidades

* **Busca de Catálogo Externa:** Integração direta com a API do **The Movie Database (TMDB)** para buscar metadados de filmes e séries com precisão.
* **Roteamento de Streaming:** Identificação de disponibilidade da mídia nas plataformas do mercado.
* **Rede Social e Match:** Sistema de amizades (Solicitar, Aceitar, Bloquear) com sugestões baseadas em cruzamento de preferências e localização.
* **Comunicação P2P:** Sistema de troca de mensagens (Chat) com status de leitura.
* **Gestão de Perfil e Histórico:** Autenticação segura de usuários e registro de mídias assistidas.

---

## Arquitetura do Sistema (Clean Architecture)

Para garantir que o software seja escalável, testável e independente de frameworks de terceiros, o backend foi desenhado sob os rígidos padrões da **Arquitetura Limpa (Clean Architecture)**. A regra de negócio não sabe que o banco de dados ou a internet existem.

```text
src/main/java/com/watchflow/watchflow
│
├── core/                               <-- 1. O NÚCLEO (Puro Java, Zero Frameworks)
│   ├── domain/                         <-- Entidades puras do negócio (Usuario, Amizade, MediaBase)
│   ├── usecase/                        <-- Regras de Orquestração (Ex: CriarContaUseCase)
│   └── gateway/                        <-- Contratos de Saída (Interfaces que o Core dita)
│
├── adapters/                           <-- 2. OS TRADUTORES (Adaptadores de Interface)
│   ├── in/                             <-- Entrada (Quem aciona o sistema)
│   │   └── controller/                 <-- Endpoints Web (Recebem JSON e acionam os Casos de Uso)
│   │
│   └── out/                            <-- Saída (Como o sistema fala com o mundo real)
│       └── gateway/                    <-- Implementação das interfaces do Core
│
└── infrastructure/                     <-- 3. O MUNDO REAL (Frameworks, DB, I/O)
    ├── config/                         <-- Painel de controle (Security, Injeção de Dependências)
    ├── web/                            <-- Tratamento global de exceções e WebSockets
    ├── persistence/                    <-- Entidades JPA, Spring Data Repositories e Mappers
    └── client/                         <-- Integrações HTTP via OpenFeign (Ex: TmdbClientImpl)

```

---

## tack Tecnológica

* **Linguagem:** Java
* **Framework:** Spring Boot
* **Banco de Dados:** PostgreSQL
* **Controle de Versão de DB:** Flyway
* **Comunicação Externa:** Spring Cloud OpenFeign
* **Tempo Real:** WebSockets
* **Infraestrutura:** Docker & Docker Compose

---

## Desmistificando o Spring Boot

Se você está explorando este repositório para aprender, é importante entender como o Spring Boot "dá vida" a essa arquitetura. O Spring funciona baseado em Injeção de Dependências e usa anotações (decorators) para gerenciar objetos (Beans). Aqui estão os principais conceitos aplicados neste projeto:

* **`@RestController` & `@RequestMapping`:** Usados na camada `adapters/in/controller`. Eles transformam uma classe Java comum em um "Garçom" da Web, capaz de receber requisições HTTP (GET, POST) e devolver arquivos JSON formatados.
* **`@Configuration` & `@Bean`:** A base da nossa Injeção de Dependência manual. Em vez de espalhar `@Service` pelo nosso núcleo, usamos classes de configuração na `infrastructure` para instanciar nossos Casos de Uso e injetar as dependências neles. O Spring guarda esses objetos na memória e os distribui onde for necessário.
* **`@Entity` & `@Table`:** Usados na camada `infrastructure/persistence`. Transformam uma classe Java em uma representação exata de uma tabela do PostgreSQL. No WatchFlow, essas classes são separadas do Domínio puro.
* **OpenFeign:** Uma ferramenta mágica declarativa. Em vez de escrever dezenas de linhas para fazer uma requisição HTTP para a API do TMDB, nós criamos uma interface e o Spring gera o código de comunicação automaticamente por baixo dos panos.

---

## Roadmap e Futuro do Projeto

O desenvolvimento atual contempla o alicerce do Backend e as integrações essenciais. Os próximos passos para o ecossistema WatchFlow incluem:

1. **Desenvolvimento do Frontend (UI/UX):** Construção da interface de usuário utilizando **Next.js** (React) para entregar uma experiência fluida, responsiva e otimizada para SEO.
2. **Implementação de Mappers:** Refinamento da separação entre DTOs, Entidades JPA e Entidades de Domínio para blindar totalmente o Core.
3. **Módulo de Streaming ao Vivo:** Integração de salas de "Watch Party", permitindo que amigos sincronizem a exibição de filmes enquanto interagem pelo chat em tempo real via WebSockets.
