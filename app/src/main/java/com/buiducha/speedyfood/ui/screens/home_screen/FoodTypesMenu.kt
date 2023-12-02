package com.buiducha.speedyfood.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.model.FoodTypes
import com.buiducha.speedyfood.ui.theme.LightGray
import com.buiducha.speedyfood.ui.theme.Orange
import com.buiducha.speedyfood.ui.theme.TextNormalStyle

@Preview
@Composable
private fun FoodTypesMenuPreview() {
    FoodTypesMenu {}
}

@Composable
fun FoodTypesMenu(
    modifier: Modifier = Modifier,
    onCategorySelect: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(FoodTypes.values()) { foodType ->
            FoodMenuItem(
                foodTypes = foodType,
                isActive = false,
                onCategorySelect = onCategorySelect
            )
        }
    }
}

@Preview
@Composable
private fun FoodMenuItemPreview() {
    FoodMenuItem(foodTypes = FoodTypes.FastFood, isActive = true) {}
}

@Composable
fun FoodMenuItem(
    foodTypes: FoodTypes,
    isActive: Boolean,
    onCategorySelect: (Int) -> Unit
) {
    val boxSize = if (isActive) 64.dp else 60.dp
    val bgColor = if (isActive) Orange else LightGray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = CircleShape)
                .clickable {
                    onCategorySelect(foodTypes.label)
                }
                .background(
                    color = bgColor,
                )
                .size(boxSize)
        ) {
            Image(
                painter = painterResource(id = foodTypes.icon),
                contentDescription = stringResource(id = R.string.image_des),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
        Text(
            text = stringResource(id = foodTypes.label),
            style = TextNormalStyle,
        )
    }
}



