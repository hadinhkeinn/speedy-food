package com.buiducha.speedyfood.ui.screens.detail_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.buiducha.speedyfood.ui.screens.shareds.TopBarButton

@Preview
@Composable
fun DetailScreenTopBarPreview() {
    DetailScreenTopBar {}
}

@Composable
fun DetailScreenTopBar(
    modifier: Modifier = Modifier,
    onNavigation: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        TopBarButton(
            imageVector = Icons.Filled.ArrowBackIosNew,
            onClick = {
                onNavigation()
            }
        )
    }
}