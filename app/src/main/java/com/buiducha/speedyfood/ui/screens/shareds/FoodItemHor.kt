package com.buiducha.speedyfood.ui.screens.shareds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import com.buiducha.speedyfood.ui.theme.Gold
import com.buiducha.speedyfood.utils.advancedShadow
import com.buiducha.speedyfood.utils.getPriceLabel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Preview
@Composable
fun FoodItemHorPreview() {
//    FoodItemHor(food = FoodData(
//        imageId = R.drawable.beef_steak,
//        label = "Beef steak",
//        price = ""
//    ))
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodItemHor(
    foodData: FoodData,
    modifier: Modifier = Modifier,
    onFoodSelect: (FoodData) -> Unit
) {
    Row(
        modifier = modifier
            .advancedShadow(
                color = Color.Gray,
                alpha = 0.05f,
                cornersRadius = 16.dp,
                shadowBlurRadius = 10.dp,
                offsetY = 1.dp
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onFoodSelect(foodData)
            }
            .background(
                color = Color.White,
            )
            .padding(
                all = 16.dp
            )
            .fillMaxWidth()

    ) {
//        Image(
//            painter = painterResource(id = itemFood.imageId),
//            contentDescription = stringResource(id = R.string.image_des),
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(88.dp)
//                .fillMaxHeight()
//                .aspectRatio(1f)
//                .clip(CircleShape)
//        )
        GlideImage(
            model = foodData.imageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(108.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = foodData.name!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500
            )
            Text(
                text = foodData.getPriceLabel(),
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                repeat(4) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Gold
                    )
                }
            }

        }
    }
}