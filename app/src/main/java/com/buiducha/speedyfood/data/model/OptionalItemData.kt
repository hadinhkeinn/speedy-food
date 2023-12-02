package com.buiducha.speedyfood.data.model

data class OptionalItemData(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0
) {
    fun getPriceTag() = "+ $price $"
}
