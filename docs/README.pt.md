For English documentation, please see the [English README](README.md).

# Chat Multiplataforma em Kotlin

Este é um projeto de chat multiplataforma em Kotlin que utiliza o Compose para criar interfaces de usuário para desktop e Android, OkHttp para a comunicação com um servidor WebSocket e Gson para a serialização de mensagens.

## Recursos

- Suporte multiplataforma para Desktop e Android.
- Comunicação em tempo real por meio de um servidor WebSocket.
- Serialização e desserialização de mensagens usando Gson.
- Interface de usuário simples e elegante criada com Compose.

## Como Usar

### Pré-requisitos

- Certifique-se de ter o Android Studio ou IntelliJ IDEA configurados com as dependências Kotlin e Compose.

### Configuração

1. Clone o repositório:

   ```bash
   git clone https://github.com/eduardomcb/simple-chat-multiplatform.git
   cd simple-chat-multiplatform
   ```

2. Abra o projeto em seu ambiente de desenvolvimento.

3. Compile e execute o aplicativo no ambiente desejado (Desktop ou Android).

## Executando o aplicativo

### No desktop

Para executar o aplicativo desktop no Android Studio, selecione `desktopApp` na lista de configurações de execução e clique em **Executar**:

<img src="../readme_images/run_on_desktop.png" height="60px"><br />

<img src="../readme_images/desktop_app_running.png" height="300px">

Você também pode executar tarefas do Gradle no terminal:

- `./gradlew run` para executar o aplicativo.
- `./gradlew package` para armazenar a distribuição nativa em `build/compose/binaries`.

### No Android

Para executar o aplicativo em um emulador Android:

1. Certifique-se de ter um dispositivo virtual Android disponível. Caso contrário, [crie um](https://developer.android.com/studio/run/managing-avds#createavd).
2. Na lista de configurações de execução, selecione `androidApp`.
3. Escolha seu dispositivo virtual e clique em **Executar**:

   <img src="../readme_images/run_on_android.png" height="60px"><br />

   <img src="../readme_images/android_app_running.png" height="300px">

<details>
  <summary>Alternativamente, use o Gradle</summary>

Para instalar um aplicativo Android em um dispositivo Android real ou em um emulador, execute `./gradlew installDebug` no terminal.

</details>

## Servidor WebSocket

Este projeto de chat depende de um servidor WebSocket para funcionar. Você pode encontrar o código-fonte do servidor em Node.js no seguinte repositório:

[simple-chat-server](https://github.com/eduardomcb/simple-chat-server)

Certifique-se de configurar e executar o servidor WebSocket antes de iniciar o aplicativo de chat.

## Contribuições

Se desejar contribuir para este projeto, sinta-se à vontade para criar pull requests ou reportar problemas na seção de [Issues](https://github.com/eduardomcb/simple-chat-multiplatform/issues).

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.
