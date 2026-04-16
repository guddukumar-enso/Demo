package com.infophone.chat.presentation.model

import android.net.Uri
import com.infophone.chat.util.MessageType
import java.io.File

data class Message(
    val id: Int,
    val text: String,
    val time: String,
    val isSender: Boolean,
    val repliedTo: Message?,
    val type: MessageType = MessageType.TEXT,
    val imageUris: List<Uri> = emptyList(),
    val audioFile: File? = null,
    val amplitudes: List<Float> = emptyList(),
    val docUri: Uri? = null,
    val audioUri: Uri? = null,
    val locationUri: Uri? = null,
    val contactUri: Uri? = null,
    val fileName: String? = null,
    val fileSize: String? = null,
    val duration: String? = null
)

