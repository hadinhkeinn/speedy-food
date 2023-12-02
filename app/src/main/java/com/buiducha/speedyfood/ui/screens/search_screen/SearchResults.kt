package com.buiducha.speedyfood.ui.screens.search_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.screens.shareds.FoodItemHor

@Composable
fun SearchResults(
    suggestionList: List<FoodData>,
    onFoodSelect: (FoodData) -> Unit
) {
    LazyColumn {
        items(suggestionList) {food ->
            FoodItemHor(
                foodData = food,
                onFoodSelect = onFoodSelect,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}