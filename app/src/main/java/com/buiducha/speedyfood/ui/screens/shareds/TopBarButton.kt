package com.buiducha.speedyfood.ui.screens.shareds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.buiducha.speedyfood.utils.advancedShadow

@Composable
fun TopBarButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .advancedShadow(
                color = Color.Gray,
                alpha = 0.4f,
                cornersRadius = 10.dp,
                shadowBlurRadius = 10.dp,
                offsetY = 2.dp
            )
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
            )
            .clickable {
                onClick()
            }
            .padding(4.dp)

    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
        )
    }
}