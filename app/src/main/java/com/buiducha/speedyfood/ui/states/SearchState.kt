package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.FoodData

data class SearchState(
    val searchQuery: String = "",
    val suggestionList: List<FoodData> = emptyList()
)
