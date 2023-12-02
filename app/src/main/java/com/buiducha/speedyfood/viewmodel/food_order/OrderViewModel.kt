package com.buiducha.speedyfood.viewmodel.food_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.model.OrderData
import com.buiducha.speedyfood.data.repository.CartRepository
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.OrderState
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.LocationViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class OrderViewModel(
    private val locationViewModel: LocationViewModel
) : ViewModel() {
    private val cartRepository = CartRepository.get()
    private val fireBaseRepository = FireBaseRepository.get()
    private val _orderState = MutableStateFlow(OrderState())
    val orderState: StateFlow<OrderState> = _orderState.asStateFlow()

    init {
        getCart()
        getLocation()
    }

    fun setNote(note: String) {
        _orderState.value = _orderState.value.copy(
            note = note
        )
    }

    private fun getLocation() {
        viewModelScope.launch {
            locationViewModel.geocoding.collect {location ->
                _orderState.value = _orderState.value.copy(
                    address = location
                )
            }
        }
        Log.d(TAG, "getLocation in order")
    }

    private fun getCart() {
        viewModelScope.launch {
            cartRepository.getCart().collect {cartItems ->
                val newFoodItems = _orderState.value.foodItems.toMutableList()

                cartItems.forEach { cart ->
                    val foodItem = suspendCoroutine { continuation ->
                        fireBaseRepository.getFood(foodId = cart.foodId) { foodItem ->
                            continuation.resume(foodItem)
                        }
                    }
                    newFoodItems += foodItem
                }
                Log.d(CartViewModel.TAG, newFoodItems.size.toString())
                _orderState.value = _orderState.value.copy(
                    cartItems = cartItems,
                    foodItems = newFoodItems
                )
            }
        }
    }

    fun placeOrder(
        onOrderSuccessful: () -> Unit,
        onOrderFailure: () -> Unit
    ) {
        val order = OrderData(
            userId = fireBaseRepository.getCurrentUser()?.uid!!,
            address = orderState.value.fullAddress,
            totalPrice = orderState.value.totalPrice,
            orderDate = "26/09/2003",
            note = orderState.value.note,
            itemList = orderState.value.cartItems
        )

        fireBaseRepository.placeOrder(
            orderData = order,
            onOrderSuccess = {
                viewModelScope.launch {
                    cartRepository.deleteAll()
                }
                onOrderSuccessful()
            },
            onOrderFailure = {
                onOrderFailure()
            }
        )
    }
    companion object {
        const val TAG = "OrderViewModel"
    }
}