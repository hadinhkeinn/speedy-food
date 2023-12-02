package com.buiducha.speedyfood.ui.screens.home_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.screens.shareds.FoodItemVer
import com.buiducha.speedyfood.ui.theme.TextSemiBoldStyle

@Preview
@Composable
fun RecommendedFoodsPreview() {
//    RecommendedFoods()
}
@Composable
fun RecommendedFoods(
    foodList: List<FoodData>,
    modifier: Modifier = Modifier,
    onFoodSelect: (FoodData) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.recommended_food),
            style = TextSemiBoldStyle,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(foodList) {item ->
//                Log.d("This is a log", item.toString())

                FoodItemVer(
                    food = item
                ) {food ->
                    onFoodSelect(food)
                }
            }
        }
    }
}

data class ItemFood(
    @DrawableRes val imageId: Int,
    val label: String,
    val price: String
)

val foodList = listOf(
    ItemFood(
        imageId = R.drawable.beef_steak,
        label = "Bit tet",
        price = "35$"
    ),
    ItemFood(
        imageId = R.drawable.burger,
        label = "Hamburger",
        price = "40$"
    ),
    ItemFood(
        imageId = R.drawable.chicken_rice,
        label = "Com ga",
        price = "60$"
    ),
    ItemFood(
        imageId = R.drawable.food,
        label = "Salad",
        price = "15$"
    )
)