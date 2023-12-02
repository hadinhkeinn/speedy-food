package com.buiducha.speedyfood.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.buiducha.speedyfood.data.model.CartItemData

@Database(entities = [CartItemData::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}