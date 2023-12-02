package com.buiducha.speedyfood.ui.screens.shareds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    weight: Double = 8.0,
    color: Color = Color.LightGray
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(weight.dp)
            .background(color)
    )
}