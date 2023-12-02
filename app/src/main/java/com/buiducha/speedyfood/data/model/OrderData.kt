package com.buiducha.speedyfood.data.model

import java.util.UUID

data class OrderData(
    val orderId: String = UUID.randomUUID().mostSignificantBits.toString(),
    val userId: String = "",
    val address: String = "",
    val totalPrice: Double = 0.0,
    val orderDate: String = "",
    val note: String = "",
    val orderStatus: Boolean = false,
    val itemList: List<CartItemData> = emptyList()
)
