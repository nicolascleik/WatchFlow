# Comandos do Terminal para o projeto WatchFlow

Este arquivo reúne os comandos principais para compilar, testar e executar o projeto Java Maven usando o wrapper `mvnw.cmd` no terminal integrado do VS Code.

> No Windows, o terminal integrado do VS Code normalmente usa PowerShell como padrão. Por isso ele mostra `powershell`, mas os comandos abaixo funcionam normalmente nele.

> Abra o terminal integrado do VS Code com `Ctrl+`` ou pelo menu `Terminal > Novo Terminal`.

## 1. Entrar na pasta do projeto
```
cd "C:\Users\Brito\OneDrive\Área de Trabalho\projeto 2\WatchFlow"
```
Serve para posicionar o terminal na pasta do projeto onde existe o `mvnw.cmd`.

## 2. Compilar o projeto e gerar o pacote
```
.\mvnw.cmd clean package
```
Serve para limpar os artefatos anteriores, compilar o código, executar os testes e gerar o pacote JAR em `target/`.

## 3. Executar os testes unitários
```
.\mvnw.cmd test
```
Serve para rodar todos os testes automatizados do projeto.

## 4. Executar a aplicação localmente
```
.\mvnw.cmd spring-boot:run
```
Serve para iniciar a aplicação Spring Boot diretamente do código-fonte.

## 5. Executar o JAR gerado (após o pacote)
```
java -jar target\WatchFlow-0.0.1-SNAPSHOT.jar
```
Serve para executar a aplicação a partir do artefato JAR gerado pelo build. Ajuste o nome do JAR se for diferente.

## 6. Exibir a árvore de dependências
```
.\mvnw.cmd dependency:tree
```
Serve para listar todas as dependências do projeto e verificar conflitos ou versões usadas.

## 7. Limpar a pasta de build
```
.\mvnw.cmd clean
```
Serve para remover todos os arquivos gerados pelo build anterior, preparando para um build limpo.
