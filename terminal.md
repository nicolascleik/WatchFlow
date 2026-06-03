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

## 6. Verificar apenas a compilação sem rodar os testes completos
```
.\mvnw.cmd -q test-compile
```
Serve para compilar o código de produção e de teste sem exibir saída detalhada, confirmando se o projeto está pronto para executar.

## 7. Executar os testes unitários
```
.\mvnw.cmd test
```
Serve para rodar todos os testes automatizados do projeto.

## 8. Executar a aplicação localmente
```
.\mvnw.cmd spring-boot:run
```
Serve para iniciar a aplicação Spring Boot diretamente do código-fonte.

## 9. Executar o JAR gerado (após o pacote)
```
java -jar target\WatchFlow-0.0.1-SNAPSHOT.jar
```
Serve para executar a aplicação a partir do artefato JAR gerado pelo build. Ajuste o nome do JAR se for diferente.

## 10. Exibir a árvore de dependências
```
.\mvnw.cmd dependency:tree
```
Serve para listar todas as dependências do projeto e verificar conflitos ou versões usadas.

## 11. Limpar a pasta de build
```
.\mvnw.cmd clean
```
Serve para remover todos os arquivos gerados pelo build anterior, preparando para um build limpo.
