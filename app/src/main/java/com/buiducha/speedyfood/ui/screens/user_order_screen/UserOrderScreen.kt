package com.buiducha.speedyfood.ui.screens.user_order_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buiducha.speedyfood.ui.theme.Ivory
import com.buiducha.speedyfood.viewmodel.food_order.UserOrderViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserOrderScreen(
    userOrderViewModel: UserOrderViewModel = viewModel()
) {
    val userOrderState by userOrderViewModel.userOrderState.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            FeedbackBottomSheet(
                feedbackContent = userOrderState.feedbackContent,
                onFeedbackChange = {
                    userOrderViewModel.setFeedbackContent(it)
                },
                onFeedbackSubmit = {
                    userOrderViewModel.addFeedback()
                    userOrderViewModel.setFeedbackContent("")
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Ivory)
                .verticalScroll(rememberScrollState())
        ) {
            userOrderState.orderList.forEach { orderData ->
                OrderItem(
                    orderItem = orderData,
                    isReceivedFeedback = userOrderViewModel.checkFeedback(orderData.orderId),
                    onItemFeedback = { orderId ->
                        userOrderViewModel.setSelectedOrder(orderId)
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                )
            }
        }
    }
}