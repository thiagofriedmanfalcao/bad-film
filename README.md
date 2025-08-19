# Orientações

### Executando a aplicação

Após realizar o download do projeto para a sua máquina,

Através do prompt de comando, vá até o diretório do projeto bad-film e 

Execute o comando abaixo para iniciar a aplicação

```bash
./gradlew bootRun
```

Em um novo prompt execute o GET para o endpoint que irá retornar a listagem com intervalo de prêmios

```bash
curl --location 'http://localhost:9091/bad-films/interval-winners'
```

Com isso irá obter a lista com intervalo de prêmios conforme exemplo abaixo

```json
{"min":[{"producer":"Joel Silver","interval":1,"previousWin":1990,"followingWin":1991}],"max":[{"producer":"Matthew Vaughn","interval":13,"previousWin":2002,"followingWin":2015}]}
```

### Executando os testes

Após realizar o download do projeto para a sua máquina,

Através do prompt de comando, vá até o diretório do projeto bad-film e

Execute o comando abaixo para executar os testes

```bash
./gradlew test
```
