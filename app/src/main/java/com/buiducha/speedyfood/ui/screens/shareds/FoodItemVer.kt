package com.buiducha.speedyfood.ui.screens.shareds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.theme.GrayBrush
import com.buiducha.speedyfood.utils.advancedShadow
import com.buiducha.speedyfood.utils.getPriceLabel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview
@Composable
fun FoodItemPreview() {
//    FoodItem(
//        modifier = Modifier
//            .padding(16.dp)
//    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodItemVer(
    food: FoodData,
    modifier: Modifier = Modifier,
    onFoodSelect: (FoodData) -> Unit
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .advancedShadow(
                color = Color.Gray,
                alpha = 0.1f,
                cornersRadius = 16.dp,
                shadowBlurRadius = 8.dp,
                offsetX = 1.dp,
                offsetY = 6.dp
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onFoodSelect(food)
            }
            .background(
                brush = GrayBrush,
            )
            .padding(16.dp)

    ) {
        GlideImage(
            model = food.imageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = food.name!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = food.getPriceLabel(),
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            color = Color.DarkGray
        )
    }
}