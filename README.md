# WatchFlow — Streaming Locator & Social Network

> Uma plataforma inteligente de descoberta de filmes e séries unida a uma experiência social completa para cinéfilos.

---

## Sobre o Projeto

O **WatchFlow** nasceu para resolver um dos maiores problemas atuais do entretenimento digital: a fragmentação dos streamings.

Hoje, um usuário muitas vezes sabe o que deseja assistir, mas não sabe **em qual plataforma o conteúdo está disponível**. O WatchFlow centraliza essa busca e adiciona uma camada social poderosa, conectando pessoas através dos seus gostos cinematográficos.

A proposta do projeto vai além de um simples buscador de filmes. O sistema foi arquitetado para funcionar como uma rede social especializada em cinema e séries, permitindo:

* Descobrir onde assistir determinado conteúdo
* Criar conexões com pessoas de gostos parecidos
* Conversar em tempo real
* Receber recomendações personalizadas
* Registrar histórico de conteúdos assistidos
* Criar uma experiência social baseada em entretenimento

---

# O Que o WatchFlow Faz

## Busca Inteligente de Filmes e Séries

A plataforma integra diretamente com a API do TMDB (*The Movie Database*) para buscar:

* Filmes
* Séries
* Episódios
* Categorias
* Avaliações
* Imagens
* Metadados

Além disso, o sistema identifica:

* Em qual streaming o conteúdo está disponível
* Se está disponível para aluguel
* Se ainda não foi lançado
* Se o conteúdo não existe nas plataformas integradas

---

## Localizador de Streaming

O usuário poderá pesquisar:

* “Onde assistir Interestelar?”
* “Qual streaming possui Breaking Bad?”
* “Oppenheimer está disponível para aluguel?”

O sistema irá responder dinamicamente com os serviços disponíveis.

Exemplo:

| Filme        | Plataforma         | Tipo       |
| ------------ | ------------------ | ---------- |
| Interstellar | HBO Max            | Assinatura |
| John Wick 4  | Prime Video        | Aluguel    |
| Deadpool 3   | Ainda indisponível | Em breve   |

---

# Rede Social Integrada

O WatchFlow também funciona como uma rede social nichada para fãs de cinema.

## Funcionalidades Sociais

### Sistema de Amizade

* Enviar solicitação
* Aceitar amizade
* Bloquear usuários
* Remover amizade

---

### Chat em Tempo Real

Usuários poderão conversar via:

* WebSockets
* Mensagens privadas
* Atualização em tempo real
* Status de leitura

---

### Match Inteligente de Usuários

As sugestões de amizade serão feitas com base em:

* Preferências de categorias
* Histórico de filmes
* Localização aproximada
* Interesses em comum

Exemplo:

> “Usuários próximos de você que gostam de Ficção Científica e Terror”

---

# Sistema de Login

O sistema possui autenticação baseada em:

* E-mail
* Senha criptografada
* JWT futuramente
* Controle de sessão
* Spring Security

Durante o cadastro o usuário poderá selecionar:

* Gêneros favoritos
* Preferências
* Interesses cinematográficos

Essas informações alimentam:

* O sistema de recomendações
* Sugestões sociais
* Descoberta de conteúdo

---

# Arquitetura do Projeto

O backend do WatchFlow foi desenvolvido utilizando os conceitos de:

* **Clean Architecture**
* **DDD (Domain Driven Design)**
* **SOLID**
* **Ports & Adapters**
* **Separação total entre regra de negócio e framework**

O objetivo é garantir:

* Alta escalabilidade
* Fácil manutenção
* Testabilidade
* Independência do Spring Boot
* Facilidade de troca de tecnologias futuramente

---

# Estrutura do Projeto

```txt
src/main/java/com/watchFlow

│
├── core/                               <-- 1. O NÚCLEO (Zero Spring, Zero Dependências Externas)
│
│   ├── domain/
│   │
│   │   ├── usuario/                    <-- Entidades de usuário e regras puras
│   │   ├── amizade/                    <-- Regras de amizade e relacionamentos
│   │   ├── chat/                       <-- Mensagens e comunicação
│   │   └── midia/                      <-- Filme, Série, Episódio e abstrações
│   │
│   └── usecase/                        <-- Casos de uso do sistema
│
│       ├── usuario/
│       ├── amizade/
│       ├── chat/
│       └── catalogo/
│
├── adapters/                           <-- Camada tradutora
│
│   ├── in/
│   │
│   │   └── controller/                 <-- Endpoints REST da aplicação
│   │
│   └── out/
│
│       └── gateway/                    <-- Interfaces de comunicação externa
│
└── infrastructure/                     <-- Camada de infraestrutura real
│
    ├── config/                         <-- Segurança, Beans e configurações
    │
    ├── web/                            <-- WebSockets e Exceptions globais
    │
    ├── persistence/                    <-- PostgreSQL + Hibernate
    │
    │   ├── entity/                     <-- Classes JPA
    │   ├── repository/                 <-- Spring Data JPA
    │   └── mapper/                     <-- Conversores Entity ↔ Domain
    │
    └── client/
        └── tmdb/                       <-- Integração OpenFeign com TMDB
```

---

# Explicando Cada Camada da Arquitetura

## `core/` → O Coração da Aplicação

Essa camada não conhece:

* Spring
* Banco de dados
* HTTP
* APIs externas

Ela contém apenas:

* Regras de negócio
* Entidades puras
* Casos de uso

Isso significa que a regra do sistema continua funcionando mesmo que:

* PostgreSQL seja trocado por MongoDB
* Spring seja trocado futuramente
* A API do TMDB deixe de existir

---

## `adapters/` → Tradutores

A função dessa camada é converter o “mundo externo” para algo que o Core entenda.

Exemplo:

* JSON → Objeto Java
* HTTP → Caso de uso
* Banco → Entidade de domínio

---

## `infrastructure/` → O Mundo Real

Aqui ficam:

* Spring Boot
* PostgreSQL
* WebSockets
* OpenFeign
* Security
* Docker
* Beans

É a única camada que conhece tecnologias externas.

---

# Banco de Dados

O sistema foi desenhado pensando em escalabilidade social.

## Principais tabelas:

### Usuario

Armazena:

* Nome
* E-mail
* Senha criptografada
* Cidade
* Estado

---

### Categorias

Representa:

* Terror
* Comédia
* Drama
* Sci-Fi
* etc.

---

### Categoria de Interesse

Tabela N:N entre:

* Usuário
* Categorias favoritas

---

### Filmes Assistidos

Registra:

* Filmes vistos
* Data
* Histórico do usuário

---

### Amizades

Sistema bidirecional contendo:

* Solicitações
* Aceite
* Bloqueios
* Status

---

### Mensagens

Chat privado contendo:

* Remetente
* Destinatário
* Texto
* Status de leitura
* Data/hora

---

# Integração com APIs Externas

O WatchFlow utiliza:

## TMDB — The Movie Database

Fonte principal de:

* Filmes
* Séries
* Imagens
* Descrições
* Gêneros
* Popularidade
* Avaliações

A comunicação é feita utilizando:

* OpenFeign
* Spring Cloud
* Clients desacoplados

---

# Stack Tecnológica

## Backend

* Java 21+
* Spring Boot
* Spring Security
* Spring Data JPA
* OpenFeign
* WebSocket
* Validation
* Lombok

---

## Banco de Dados

* PostgreSQL
* Flyway Migration

---

## Infraestrutura

* Docker
* Docker Compose

---

## APIs

* TMDB API

---

# Docker e Infraestrutura

O projeto foi pensado desde o início para ser containerizado.

O ambiente utilizará:

* Backend Spring Boot
* PostgreSQL
* Containers independentes
* Rede compartilhada via Docker Compose

Benefícios:

* Facilidade de deploy
* Ambientes padronizados
* Setup rápido
* Desenvolvimento simplificado

---

# Entendendo o Spring Boot no Projeto

Este projeto também funciona como uma documentação prática para iniciantes em Spring Boot.

---

# O que é um Bean?

Um **Bean** é um objeto controlado pelo Spring.

Ao invés de criar objetos manualmente com:

```java
new UsuarioService()
```

O Spring:

* instancia
* gerencia
* injeta
* compartilha

automaticamente.

---

# Principais Anotações do Projeto

## `@RestController`

Transforma uma classe em um endpoint HTTP.

Exemplo:

```java
@RestController
@RequestMapping("/users")
public class UserController {
}
```

Responsável por:

* receber requisições
* devolver JSON
* conversar com os casos de uso

---

## `@RequestMapping`

Define rotas da API.

Exemplo:

```java
@RequestMapping("/movies")
```

---

## `@GetMapping`, `@PostMapping`

Mapeiam métodos HTTP.

```java
@GetMapping
@PostMapping
```

---

## `@Service`

Marca uma classe de serviço do Spring.

Apesar disso, o projeto busca reduzir dependência excessiva de `@Service` dentro do Core, priorizando injeção via configuração.

---

## `@Configuration`

Classe responsável por configuração manual do Spring.

Exemplo:

```java
@Configuration
public class BeanConfig {
}
```

---

## `@Bean`

Define manualmente objetos gerenciados pelo Spring.

Exemplo:

```java
@Bean
public FazerLoginUseCase fazerLoginUseCase() {
    return new FazerLoginUseCase(...);
}
```

---

## `@Entity`

Transforma uma classe em tabela do banco.

```java
@Entity
@Table(name = "usuarios")
```

---

## `@Repository`

Representa acesso ao banco de dados.

Muito utilizado com Spring Data JPA.

---

## `@Autowired`

Permite injeção automática de dependências.

Embora atualmente exista, o projeto tende a priorizar injeção por construtor.

---

## `@Component`

Marca componentes genéricos controlados pelo Spring.

---

## `@Transactional`

Controla transações do banco.

Se algo falhar:

* rollback automático
* consistência garantida

---

## `@Valid`

Validação automática de DTOs.

Exemplo:

```java
public ResponseEntity<?> criar(@Valid @RequestBody UserDTO dto)
```

---

# Fluxo Interno da Aplicação

## Exemplo de Login

```txt
Controller
   ↓
UseCase
   ↓
Gateway
   ↓
Repository
   ↓
Banco de Dados
```

---

# Segurança

A aplicação utiliza:

* Spring Security
* Criptografia de senha
* Controle de autenticação
* Futuro suporte JWT
* Proteção de rotas

---

# Comunicação em Tempo Real

O chat será construído usando:

* WebSockets
* Sessões persistentes
* Comunicação instantânea

Permitindo:

* Mensagens em tempo real
* Atualização sem refresh
* Experiência semelhante a apps modernos

---

# Futuro do Projeto

O WatchFlow foi arquitetado pensando em expansão futura.

---

# Frontend Web

Planeja-se o desenvolvimento de uma interface moderna utilizando:

## Possíveis tecnologias

* Next.js
* React
* TailwindCSS
* ShadCN/UI

Objetivos:

* UI moderna
* SSR
* SEO otimizado
* Alta performance
* Responsividade

---

# Sistema de Recomendação

* Preferências
* Histórico
* Similaridade entre usuários

---

# Objetivos Educacionais

Este repositório foi estruturado baseado em:

* Spring Boot
* Arquitetura Limpa
* SOLID
* JPA/Hibernate
* WebSockets
* Docker
* PostgreSQL
* APIs REST
* OpenFeign
* Separação de responsabilidades
* Design de software

---
