package com.buiducha.speedyfood.ui.screens.cart_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.theme.PrimaryColor

@Composable
fun CheckoutBar(
    totalPrice: Double,
    subTotal: Double,
    deliveryFee: Double,
    isCheckoutAvailable: Boolean,
    modifier: Modifier = Modifier,
    onCheckout: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
//            .background(Color.Red)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 24.dp,
                top = 8.dp
            )
    ) {
        BillingItem(
            fieldName = stringResource(id = R.string.sub_total),
            value = "$subTotal $"
        )
        BillingItem(
            fieldName = stringResource(id = R.string.delivery),
            value = "$deliveryFee $"
        )
        BillingItem(
            fieldName = stringResource(id = R.string.total),
            value = "$totalPrice $"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CheckoutButton(
            onCheckout = onCheckout,
            isCheckoutAvailable = isCheckoutAvailable
        )
    }
}

@Composable
fun BillingItem(
    fieldName: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = fieldName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CheckoutButton(
    isCheckoutAvailable: Boolean,
    onCheckout: () -> Unit
) {
    Button(
        onClick = {
            onCheckout()
        },
        shape = RoundedCornerShape(20),
        enabled = isCheckoutAvailable,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.checkout),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
}