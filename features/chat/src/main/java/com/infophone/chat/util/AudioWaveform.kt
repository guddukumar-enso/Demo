package com.infophone.chat.util

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.infophone.ui.theme.Indigo900

@Composable
fun AudioWaveform(
    amplitudes: List<Float>,
    progress: Float,
    modifier: Modifier = Modifier,
    activeColor: Color = Indigo900,
    inactiveColor: Color = Color.LightGray.copy(alpha = 0.5f)
) {
    Canvas(modifier = modifier) {
        val barWidth = 3.dp.toPx()
        val gap = 2.dp.toPx()
        val itemWidth = barWidth + gap

        // 1. Determine how many bars we can actually draw
        val maxBarsVisible = (size.width / itemWidth).toInt()

        // 2. Downsample the amplitudes to fit the screen width exactly
        // This ensures the waveform looks consistent regardless of audio length
        val displayAmplitudes = if (amplitudes.size > maxBarsVisible) {
            val step = amplitudes.size.toFloat() / maxBarsVisible
            (0 until maxBarsVisible).map { i ->
                amplitudes[(i * step).toInt()]
            }
        } else {
            amplitudes
        }

        displayAmplitudes.forEachIndexed { index, amplitude ->
            val x = index * itemWidth

            // 3. Check if this specific bar has been "played" yet
            val isPlayed = (index.toFloat() / displayAmplitudes.size) <= progress

            // 4. Normalize height
            val barHeight = (amplitude * size.height).coerceAtLeast(4.dp.toPx())
            val yOffset = (size.height - barHeight) / 2

            drawRoundRect(
                color = if (isPlayed) activeColor else inactiveColor,
                topLeft = Offset(x, yOffset),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2)
            )
        }
    }
}