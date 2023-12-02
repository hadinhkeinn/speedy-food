package com.buiducha.speedyfood.ui.theme

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val PrimaryColor = Color(0xFFdd6e3e)

val Shade = Color(0xFFf5f5f5)
val Orange = Color(0xFFf09150)
val LightGray = Color(0xFFeeeeee)
val VeryLightGray = Color(0xFFfbfbfb)
val Ivory = Color(0xFFfcfcfc)
val Gold = Color(0xFFFFD700)
val DarkGray = Color(0xFF393939)
val DarkGreen = Color(0xFF57b667)
val GrayBrush = Brush.linearGradient(
    listOf(Ivory, Color.White)
)
val MarianBlue = Color(0xFF023e8a)

@Composable
fun AuthenticTextFieldColor() = TextFieldDefaults.colors(
    focusedContainerColor = LightGray,
    unfocusedContainerColor = LightGray,
    disabledContainerColor = LightGray,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)