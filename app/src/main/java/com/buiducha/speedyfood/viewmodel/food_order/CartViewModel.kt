package com.buiducha.speedyfood.viewmodel.food_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.repository.CartRepository
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CartViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val cartRepository = CartRepository.get()
    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    init {
        getCart()
    }

    fun deleteItem(cartId: String) {
        viewModelScope.launch {
            cartRepository.deleteItem(cartId)
        }
    }

    fun addQuantity(cartId: String) {
        val quantity = _cartState.value.cartItems.find { it.cartItemId == cartId }?.quantity!! + 1
        viewModelScope.launch {
            cartRepository.updateQuantity(cartId, quantity)
        }
    }

    fun subQuantity(cartId: String) {
        val quantity = _cartState.value.cartItems.find { it.cartItemId == cartId }?.quantity!! - 1
        if (quantity >= 0) {
            viewModelScope.launch {
                cartRepository.updateQuantity(cartId, quantity)
            }
        }

    }

    private fun getCart() {
        viewModelScope.launch {
            cartRepository.getCart().collect {cartItems ->
                val newFoodItems = _cartState.value.foodItems.toMutableList()

                cartItems.forEach { cart ->
                    val foodItem = suspendCoroutine { continuation ->
                        fireBaseRepository.getFood(foodId = cart.foodId) { foodItem ->
                            continuation.resume(foodItem)
                        }
                    }
                    newFoodItems += foodItem
                }
                Log.d(TAG, newFoodItems.size.toString())
                _cartState.value = _cartState.value.copy(
                    cartItems = cartItems,
                    foodItems = newFoodItems
                )
            }
        }
    }
    companion object {
        const val TAG = "CartViewModel"
    }
}