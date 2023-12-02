package com.buiducha.speedyfood.ui.screens.shareds

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleTopBar(
    onBackListener: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TopBarButton(
            imageVector = Icons.Filled.ArrowBackIosNew,
            onClick = {
                onBackListener()
            }
        )
    }
}