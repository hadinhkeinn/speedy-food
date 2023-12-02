package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.data.model.OptionalItemData
import java.text.DecimalFormat

data class DetailState(
    var food: FoodData? = null,
    var itemQuantity: Int = 1,
    var toppingList: Set<OptionalItemData> = setOf(),
    var addedTopping: MutableSet<String> = mutableSetOf()
) {
    private val df = DecimalFormat("#.##")
    val price: Double
        get() = df.format(
            (food?.price!! + addedTopping.sumOf {
                    topping -> toppingList.find { it.id == topping }?.price ?: 0.0
            })
        ).replace(",",".").toDouble()

    val totalPrice: Double
        get() = df.format(
            (food?.price!! + addedTopping.sumOf {
                    topping -> toppingList.find { it.id == topping }?.price ?: 0.0
            }) * itemQuantity
        ).replace(",", ".").toDouble()
}
