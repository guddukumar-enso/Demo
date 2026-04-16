package com.infophone.chat.presentation.message

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infophone.chat.presentation.model.Message
import com.infophone.chat.util.AudioManager
import com.infophone.chat.util.MessageType
import com.infophone.chat.util.convertPcmToWav
import com.infophone.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val audioManager: AudioManager
): ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(
        listOf(
            Message(1, "Hello, bro! Can you help me?", "2:56 PM", false, repliedTo = null),
            Message(2, "Yeah sure, tell me.", "2:57 PM", true, repliedTo = null),
            Message(3, "I need help with Jetpack Compose UI.", "2:58 PM", false, repliedTo = null),
            Message(4, "No worries, I got you 😄", "2:59 PM", true, repliedTo = null),
            Message(5, "Meeting location", "10:01 AM", false, type = MessageType.LOCATION, repliedTo = null),
//            Message(6, "Voice note from yesterday", "10:06 AM", false, repliedTo = null,MessageType.AUDIO, duration = "1:15", fileSize = "800 KB"),
//            Message(7, "Project Guidelines.pdf", "10:10 AM", true, repliedTo = null,MessageType.DOCUMENT, fileName = "Guidelines.pdf", fileSize = "2.4 MB"),
//            Message(8, "Check this video", "10:12 AM", false, repliedTo = null,MessageType.VIDEO, duration = "0:45"),
//            Message(9, "Shubham Kande", "10:12 AM", true, repliedTo = null,MessageType.CONTACT)
        ).asReversed() // Keep newest at index 0 for reverseLayout
    )
    val messages: StateFlow<List<Message>> = _messages

    // Recording States
    var isRecording by mutableStateOf(false)
        private set
    var isPaused by mutableStateOf(false)
        private set
    var duration by mutableIntStateOf(0)
        private set
    val amplitudes = mutableStateListOf<Float>()

    private var timerJob: Job? = null

    fun sendTextMessage(text: String, replyTo: Message? = null) {
        val newMessage = Message(
            id = UUID.randomUUID().toString().hashCode(),
            text = text,
            time = formatTimestamp(System.currentTimeMillis()),
            isSender = true,
            type = MessageType.TEXT,
            repliedTo = replyTo
        )
        _messages.update { listOf(newMessage) + it }
    }

    fun sendImageMessage(uris: List<Uri>, message: String) {
        val newMessage = Message(
            id = UUID.randomUUID().toString().hashCode(),
            text = message,
            time = formatTimestamp(System.currentTimeMillis()),
            isSender = true,
            imageUris = uris,
            type = MessageType.IMAGE,
            repliedTo = null
        )
        _messages.update { listOf(newMessage) + it }
    }

    fun sendVoiceMessage(file: File, amps: List<Float>) {
        val newMessage = Message(
            id = UUID.randomUUID().toString().hashCode(),
            "",
            time = formatTimestamp(System.currentTimeMillis()),
            isSender = true,
            audioFile = file,
            amplitudes = amps,
            type = MessageType.RECORDING,
            repliedTo = null
        )
        _messages.update { listOf(newMessage) + it }
    }

    fun sendDocumentMessage(uri: Uri) {
        val newMessage = Message(
            id = UUID.randomUUID().toString().hashCode(),
            text = "",
            time = formatTimestamp(System.currentTimeMillis()),
            isSender = true,
            docUri = uri,
            type = MessageType.DOCUMENT,
            repliedTo = null
        )
        _messages.update { listOf(newMessage) + it }
    }

    fun startRecording(pcmFile: File) {
        amplitudes.clear()
        duration = 0
        isRecording = true
        isPaused = false

        startTimer()

        viewModelScope.launch(Dispatchers.IO) {
            audioManager.startRecording(pcmFile) { amplitude ->
                if (!isPaused) {
                    amplitudes.add(amplitude)
                }
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isRecording) {
                if (!isPaused) {
                    delay(1000)
                    duration++
                } else {
                    delay(100) // Poll less frequently when paused
                }
            }
        }
    }

    fun togglePause() {
        isPaused = !isPaused
    }

    fun stopAndSend(pcmFile: File, filesDir: File, onComplete: (File, List<Float>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            audioManager.stop()
            isRecording = false

            val wavFile = File(filesDir, "voice_note_${System.currentTimeMillis()}.wav")
            convertPcmToWav(pcmFile, wavFile)

            if (pcmFile.exists()) pcmFile.delete()

            withContext(Dispatchers.Main) {
                val capturedAmps = amplitudes.toList()
                onComplete(wavFile, capturedAmps)
                // Add to list
                sendVoiceMessage(wavFile, capturedAmps)
            }
        }
    }

    fun deleteRecording(pcmFile: File) {
        audioManager.stop()
        isRecording = false
        if (pcmFile.exists()) pcmFile.delete()
        amplitudes.clear()
        duration = 0
    }

    fun formatTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        // "h:mm a" gives you "2:30 PM" (12-hour format)
        // "HH:mm" would give you "14:30" (24-hour format)
        val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
        return sdf.format(date)
    }

}