import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.gson.Gson
import models.Message
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient {

    private var webSocket: WebSocket? = null
    var messageStatus by mutableStateOf("Connecting...")
    var isConnected by mutableStateOf(false)
    var chatMessages by mutableStateOf(listOf<Message>())

    fun connectWebSocket(username: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("wss://simple-chat-server-48wd.onrender.com/chat")
            .addHeader("username", username)
            .build()

        val webSocketListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                messageStatus = "Connected"
                isConnected = true
                println("Conexão WebSocket aberta")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                processReceivedMessage(text)
                println("Mensagem recebida: $text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                println("Mensagem em bytes recebida: $bytes")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("Conexão sendo fechada")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                messageStatus = "Disconnected"
                isConnected = false
                println("Conexão fechada")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                messageStatus = "Disconnected"
                isConnected = false
                println("Falha na conexão: ${t.message}")
            }
        }

        webSocket = client.newWebSocket(request, webSocketListener)

    }

    fun sendMessage(message: Message) {
        try {
            val gson = Gson()

            val jsonToString = gson.toJson(message)
            println(jsonToString)
            webSocket?.send(jsonToString)
            println("Mensagem enviada: $jsonToString")
        } catch (error: Exception) {
            println("Erro ao enviar mensagem: $error")
        }

    }

    private fun processReceivedMessage(json: String) {
        val gson = Gson()

        val data = gson.fromJson(json, Message::class.java)
        chatMessages += data
    }

    fun closeWebSocket() {
        webSocket?.close(1000, "Desconexão solicitada pelo cliente")
    }
}
