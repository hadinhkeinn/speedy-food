package com.buiducha.speedyfood.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.buiducha.speedyfood.data.model.CartItemData
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_item_data")
    fun getCart(): Flow<List<CartItemData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: CartItemData)

    @Query("UPDATE cart_item_data SET quantity = :newQuantity WHERE cartItemId = :cartItemId")
    suspend fun updateQuantity(cartItemId: String, newQuantity: Int)

    @Query("DELETE FROM cart_item_data WHERE cartItemId = :cartItemId")
    suspend fun deleteItem(cartItemId: String)

    @Query("SELECT * FROM cart_item_data WHERE foodId = (:foodId) AND toppingIds = :toppingIds")
    suspend fun getItemByFoodAndToppings(foodId: String, toppingIds: String): CartItemData?

    @Query("DELETE FROM cart_item_data")
    suspend fun deleteAll()
}