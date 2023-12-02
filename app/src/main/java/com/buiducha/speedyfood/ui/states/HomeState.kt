package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.FoodData

data class HomeState(
    val foodList: List<FoodData> = emptyList(),
)
