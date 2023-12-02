package com.buiducha.speedyfood.viewmodel.food_order

import androidx.lifecycle.ViewModel
import com.buiducha.speedyfood.data.model.OrderFeedback
import com.buiducha.speedyfood.data.repository.FireBaseRepository
import com.buiducha.speedyfood.ui.states.UserOrderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserOrderViewModel : ViewModel() {
    private val fireBaseRepository = FireBaseRepository.get()
    private val _userOrderState = MutableStateFlow(UserOrderState())
    val userOrderState: StateFlow<UserOrderState> = _userOrderState.asStateFlow()

    init {
        getOrder()
        getFeedbacks()
    }

    fun checkFeedback(orderId: String): Boolean {
        return _userOrderState.value.feedbackList.any { order -> order.orderId == orderId }
    }

    private fun getFeedbacks() {
        fireBaseRepository.getFeedbacks(
            onGetFeedbackSuccess = {feedback ->
                val newList = _userOrderState.value.feedbackList
                newList += feedback
                _userOrderState.value = _userOrderState.value.copy(
                    feedbackList = newList
                )
            }
        )
    }

    fun addFeedback() {
        val feedback = OrderFeedback(
            orderId = _userOrderState.value.selectedOrder,
            content = _userOrderState.value.feedbackContent
        )
        fireBaseRepository.addFeedback(
            feedback = feedback,
            onAddSuccess = {

            },
            onAddFailure = {

            }
        )
    }

    fun setFeedbackContent(content: String) {
        _userOrderState.value = _userOrderState.value.copy(
            feedbackContent = content
        )
    }

    fun setSelectedOrder(id: String) {
        _userOrderState.value = _userOrderState.value.copy(
            selectedOrder = id
        )
    }

    private fun getOrder() {
        fireBaseRepository.getOrder(
            userId = fireBaseRepository.getCurrentUser()?.uid!!,
            onGetOrderSuccess = {orderData ->
                _userOrderState.value = _userOrderState.value.copy(
                    orderList = orderData.toMutableList()
                )
            },
            onGetOrderFailure = {

            }
        )
    }
}