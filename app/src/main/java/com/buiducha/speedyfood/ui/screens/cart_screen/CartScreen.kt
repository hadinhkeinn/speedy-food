package com.buiducha.speedyfood.ui.screens.cart_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.theme.Ivory
import com.buiducha.speedyfood.ui.theme.TextBoldStyle
import com.buiducha.speedyfood.ui.theme.TextNormalStyle
import com.buiducha.speedyfood.viewmodel.food_order.CartViewModel

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(rememberNavController())
}

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel()
) {
    val cartState by cartViewModel.cartState.collectAsState()
    var dialogVisible by remember {
        mutableStateOf(false)
    }
    var selectedItemId = ""
    Scaffold(
        topBar = {
            CartScreenTopBar(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            CheckoutBar(
                totalPrice = cartState.totalPrice,
                subTotal = cartState.subTotal,
                deliveryFee = cartState.deliveryFee,
                isCheckoutAvailable = cartState.cartItems.isNotEmpty(),
                modifier = Modifier,
                onCheckout = {
                    navController.navigate(Screen.OrderScreen.route)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Ivory)
                .padding(padding)
        ) {
            if (dialogVisible) {
                RemoveItemDialog(
                    onDismissRequest = {
                        dialogVisible = false
                    },
                    onConfirmation = {
                        cartViewModel.deleteItem(selectedItemId)
                        dialogVisible = false
                    },
                    dialogTitle = stringResource(id = R.string.remove_item)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my),
                    style = TextBoldStyle,
                    fontSize = 32.sp
                )
                Text(
                    text = stringResource(id = R.string.cart_list),
                    style = TextNormalStyle,
                    fontSize = 32.sp
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                if (cartState.foodItems.isNotEmpty()) {
                    Log.d(TAG, "not empty")
                    items(cartState.cartItems) {cartItem ->
                        val food = cartState.foodItems.find { it.id == cartItem.foodId }
                        Log.d(TAG, food.toString())
                        food?.let {
                            CartItem(
                                cartItemData = cartItem,
                                foodData = it,
                                onAddQuantity = {
                                    cartViewModel.addQuantity(cartItem.cartItemId)
                                },
                                onSubQuantity = {
                                    if (cartItem.quantity > 1) {
                                        cartViewModel.subQuantity(cartItem.cartItemId)
                                    } else {
                                        selectedItemId = cartItem.cartItemId
                                        dialogVisible = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

const val TAG = "CartScreen"