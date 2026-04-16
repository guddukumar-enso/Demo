package com.infophone.chat.util

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlin.math.sqrt

class AudioManager(private val context: Context) {
    private var audioRecord: AudioRecord? = null
    private val sampleRate = 44100
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    private val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

    @SuppressLint("MissingPermission")
    suspend fun startRecording(outputFile: File, onBufferRead: (Float) -> Unit) = withContext(Dispatchers.IO) {
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            channelConfig,
            audioFormat,
            bufferSize
        )

        val data = ShortArray(bufferSize)
        val outputStream = FileOutputStream(outputFile)

        audioRecord?.startRecording()

        while (audioRecord?.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            val read = audioRecord?.read(data, 0, bufferSize) ?: 0
            if (read > 0) {
                // 1. Calculate Amplitude (RMS) for the Waveform
                var sum = 0.0
                for (i in 0 until read) {
                    sum += data[i] * data[i]
                }
                val rms = sqrt(sum / read).toFloat()
                val normalizedAmplitude = (rms / 32768f).coerceIn(0f, 1f)

                // 2. Send to UI
                withContext(Dispatchers.Main) {
                    onBufferRead(normalizedAmplitude)
                }

                // 3. Write raw PCM data to file
                val byteData = pcmToByteArray(data, read)
                outputStream.write(byteData)
            }
        }
        outputStream.close()
    }

    fun stop() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }

    private fun pcmToByteArray(data: ShortArray, read: Int): ByteArray {
        val bytes = ByteArray(read * 2)
        for (i in 0 until read) {
            bytes[i * 2] = (data[i].toInt() and 0x00FF).toByte()
            bytes[i * 2 + 1] = (data[i].toInt() shr 8).toByte()
        }
        return bytes
    }
}