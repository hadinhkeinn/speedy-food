package com.buiducha.speedyfood.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.buiducha.speedyfood.data.local.database.StringListConverter
import java.util.UUID

@Entity("cart_item_data")
@TypeConverters(StringListConverter::class)
data class CartItemData(
    @PrimaryKey
    val cartItemId: String = UUID.randomUUID().mostSignificantBits.toString(),
    val userId: String = "",
    val foodId: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val toppingIds: List<String> = emptyList()
)