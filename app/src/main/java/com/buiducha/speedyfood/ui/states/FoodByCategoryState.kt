package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.FoodData

data class FoodByCategoryState(
    val categoryLabel: Int = 0,
    val foodList: List<FoodData> = emptyList()
)
