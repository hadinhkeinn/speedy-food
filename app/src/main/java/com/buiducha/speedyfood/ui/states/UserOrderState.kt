package com.buiducha.speedyfood.ui.states

import com.buiducha.speedyfood.data.model.OrderData
import com.buiducha.speedyfood.data.model.OrderFeedback

data class UserOrderState(
    val orderList: MutableList<OrderData> = mutableListOf(),
    val feedbackList: MutableList<OrderFeedback> = mutableListOf(),
    val selectedOrder: String = "",
    val feedbackContent: String = ""
)
