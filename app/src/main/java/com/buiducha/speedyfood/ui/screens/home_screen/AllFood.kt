package com.buiducha.speedyfood.ui.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.screens.shareds.FoodItemHor
import com.buiducha.speedyfood.ui.theme.TextSemiBoldStyle

@Composable
fun AllFood(
    foodList: List<FoodData>,
    modifier: Modifier = Modifier,
    onFoodSelect: (FoodData) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.all_food),
            style = TextSemiBoldStyle,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
        ) {
            foodList.forEach {food ->
                FoodItemHor(
                    foodData = food,
                    onFoodSelect = onFoodSelect
                )
            }
        }
    }
}