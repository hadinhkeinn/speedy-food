package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.CartItemData
import com.buiducha.speedyfood.data.model.FoodData
import java.text.DecimalFormat

data class CartState(
    var cartItems: List<CartItemData> = emptyList(),
    var foodItems: List<FoodData> = emptyList(),
) {
    private val df = DecimalFormat("#.##")

    val totalPrice: Double
        get() = df.format(cartItems.sumOf { cart -> cart.price * cart.quantity } + deliveryFee).replace(",",".").toDouble()
    val subTotal: Double
        get() = df.format(cartItems.sumOf { cart -> cart.price * cart.quantity }).replace(",",".").toDouble()

    val deliveryFee: Double
        get() = if (cartItems.isNotEmpty()) 5.0 else 0.0
}

