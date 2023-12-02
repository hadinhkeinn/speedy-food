package com.buiducha.speedyfood.data.model

data class FoodData(
    val id: String? = "",
    val name: String? = "",
    val price: Double? = 0.0,
    val imageUri: String? = "",
    val toppings: List<String> = emptyList(),
    val type: String? = ""
)
