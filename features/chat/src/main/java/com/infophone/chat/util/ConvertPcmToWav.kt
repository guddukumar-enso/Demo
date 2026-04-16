package com.infophone.chat.util

import java.io.File
import java.io.FileOutputStream

fun convertPcmToWav(pcmFile: File, wavFile: File) {
    val pcmData = pcmFile.readBytes()
    val out = FileOutputStream(wavFile)

    val totalAudioLen = pcmData.size.toLong()
    val totalDataLen = totalAudioLen + 36
    val sampleRate = 44100L
    val channels = 1
    val byteRate = 16 * sampleRate * channels / 8

    writeWavHeader(out, totalAudioLen, totalDataLen, sampleRate, channels, byteRate)
    out.write(pcmData)
    out.close()
}