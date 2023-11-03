package models

import com.google.gson.annotations.SerializedName

enum class MessageSource {
    USER,
    SERVER
}

data class Message(
    @SerializedName("username")
    val username: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("isMine")
    val isMine: Boolean = false,
    @SerializedName("source")
    val source: MessageSource
)