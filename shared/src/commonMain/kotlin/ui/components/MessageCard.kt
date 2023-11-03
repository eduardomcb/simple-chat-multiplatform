package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.Message
import models.MessageSource

@Composable
fun MessageCard(message: Message) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isMine) Alignment.End else Alignment.Start
    ) {
        if (message.source == MessageSource.SERVER) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth(0.5f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message.username,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                    Text(
                        text = " - " + message.message,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = message.username,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                if (!message.isMine) {
                    Text(
                        text = message.timestamp,
                        fontSize = 8.sp,
                        color = Color(0xFF8F96A0),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(8.dp),
            ) {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }

}