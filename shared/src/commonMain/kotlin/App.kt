import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.Message
import models.MessageSource
import ui.components.MessageCard
import ui.components.MessageInput

val ws = WebSocketClient()

@Composable
fun App() {
    MaterialTheme {
        ChatScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {

    var newMessage by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val scrollState = rememberLazyListState()

    LaunchedEffect(ws.chatMessages.size) {
        if (ws.chatMessages.isNotEmpty()) {
            scrollState.animateScrollToItem(ws.chatMessages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Chat App - ${ws.messageStatus}") })
        },
        content = {

            if (ws.isConnected) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ChatMessages(messages = ws.chatMessages, scrollState)
                    }
//                    Spacer(modifier = Modifier.weight(1f))
                    MessageInput(
                        message = newMessage,
                        onMessageChange = { newMessage = it },
                        onSendMessage = {
                            val msg = Message(
                                username = username,
                                message = newMessage,
                                isMine = true,
                                source = MessageSource.USER
                            )
                            ws.sendMessage(msg)
                            ws.chatMessages += msg
                            newMessage = ""
                        }
                    )
                }

            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        label = { Text("Username") },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (username.isNotEmpty()) {
                                ws.connectWebSocket(username)
                            }
                        }
                    ) {
                        Text("Salvar e Conectar", color = Color.White)
                    }
                }
            }

        }
    )
}

@Composable
fun ChatMessages(messages: List<Message>, scrollState: LazyListState) {
    LazyColumn(
        state = scrollState,
    ) {
        items(messages.size) { message ->
            MessageCard(message = messages[message])
        }
    }
}


expect fun getPlatformName(): String