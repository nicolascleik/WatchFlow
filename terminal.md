# Comandos do Terminal para o projeto WatchFlow

Este arquivo reúne os comandos principais para compilar, testar e executar o projeto Java Maven usando o wrapper `mvnw.cmd` no terminal integrado do VS Code.

> No Windows, o terminal integrado do VS Code normalmente usa PowerShell como padrão. Por isso ele mostra `powershell`, mas os comandos abaixo funcionam normalmente nele.

> Abra o terminal integrado do VS Code com `Ctrl+`` ou pelo menu `Terminal > Novo Terminal`.

## 1. Entrar na pasta do projeto
```
cd "C:\Users\Brito\OneDrive\Área de Trabalho\projeto 2\WatchFlow"
```
Serve para posicionar o terminal na pasta do projeto onde existe o `mvnw.cmd`.

## 2. Iniciar o banco de dados PostgreSQL com Docker Compose
```
docker compose -f compose.yaml up -d
```
Serve para iniciar o serviço de banco de dados definido em `compose.yaml` no modo destacável (em segundo plano). No projeto, isso sobe o container `postgres` necessário para o Spring Boot conectar ao banco.

## 3. Verificar os serviços Docker em execução
```
# Use quando estiver em outro diretório (especifica o arquivo Compose):
docker compose -f compose.yaml ps

# Ou, se você já estiver dentro da pasta do projeto `WatchFlow`, pode usar:
docker compose ps
```
Serve para listar os containers e mostrar o status dos serviços criados pelo Docker Compose.

## 4. Ver os logs do Docker Compose
```
docker compose logs --tail=20
```
Serve para mostrar as últimas linhas do log dos containers do Compose, ajudando a verificar se o banco está subindo corretamente.

## 5. Parar os serviços do Docker Compose
```
docker compose down
```
Serve para parar e remover os containers, rede e volumes temporários criados pelo Docker Compose. Use quando quiser desligar o ambiente do banco de dados.

## 6. Compilar o projeto e gerar o pacote
```
.\mvnw.cmd clean package
```
Serve para limpar os artefatos anteriores, compilar o código, executar os testes e gerar o pacote JAR em `target/`.

## 7. Compilar sem rodar testes
```
.\mvnw.cmd -q test-compile
```
Serve para compilar o código de produção e de teste sem exibir saída detalhada, confirmando se o projeto está pronto para executar.

## 8. Executar os testes do projeto
```
.\mvnw.cmd test
```
Serve para rodar todos os testes automatizados do projeto.

## 9. Executar os testes em modo silencioso
```
.\mvnw.cmd -q clean test
```
Serve para rodar o build completo com testes e deixar a saída mais enxuta.

## 10. Executar a aplicação localmente
```
.\mvnw.cmd spring-boot:run
```
Serve para iniciar a aplicação Spring Boot diretamente do código-fonte.

## 11. Executar o JAR gerado (após o pacote)
```
java -jar target\WatchFlow-0.0.1-SNAPSHOT.jar
```
Serve para executar a aplicação a partir do artefato JAR gerado pelo build. Ajuste o nome do JAR se for diferente.

## 12. Exibir a árvore de dependências
```
.\mvnw.cmd dependency:tree
```
Serve para listar todas as dependências do projeto e verificar conflitos ou versões usadas.

## 13. Limpar a pasta de build
```
.\mvnw.cmd clean
```
Serve para remover todos os arquivos gerados pelo build anterior, preparando para um build limpo.

## 14. Verificar se o Docker está disponível
```
docker version
```
Use para confirmar se o Docker Desktop/daemon está instalado e ativo antes de executar o `docker compose`.

## 15. Quando os testes usam H2 em memória
O projeto foi configurado para usar um banco H2 em memória durante os testes, então os comandos de teste não dependem necessariamente de PostgreSQL local.

- Configuração de teste:
  - `spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
  - `spring.jpa.hibernate.ddl-auto=create-drop`
  - `spring.flyway.enabled=false`

## 16. Observação sobre a aplicação em produção/desenvolvimento
A aplicação principal usa PostgreSQL em `src/main/resources/application.properties`:
- `spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase`
- `spring.datasource.username=myuser`
- `spring.datasource.password=secret`

Se quiser executar a aplicação real localmente, mantenha o Docker Compose ativo para o PostgreSQL.

## 17. Comandos úteis adicionais
```
# Mostrar o diretório atual
pwd

# Listar arquivos
Get-ChildItem
```

## Importante
Sempre execute os comandos a partir da raiz do projeto `WatchFlow` para garantir que `mvnw.cmd`, `pom.xml` e `compose.yaml` sejam encontrados corretamente.