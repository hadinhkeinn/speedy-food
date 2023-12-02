package com.buiducha.speedyfood.ui.screens.order_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.model.CartItemData
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.screens.shareds.HorizontalLine
import com.buiducha.speedyfood.ui.theme.LightGray
import com.buiducha.speedyfood.ui.theme.Orange

@Preview
@Composable
fun OrderSectionPreview() {
//    OrderSection()
}

@Composable
fun OrderSection(
    cartList: List<CartItemData>,
    foodList: List<FoodData>,
    subTotal: Double,
    deliveryFee: Double
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.order_summary),
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        cartList.forEach { cartItem ->
            val foodItem = foodList.find { it.id == cartItem.foodId }
            if (foodItem != null) {
                OrderSectionItem(
                    cartItemData = cartItem,
                    foodItem = foodItem
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalLine(
            weight = 2.0,
            color = LightGray
        )
        Spacer(modifier = Modifier.height(4.dp))
        BillSummary(
            subTotal = subTotal,
            deliveryFee = deliveryFee
        )
    }
}

@Composable
private fun BillSummary(
    subTotal: Double,
    deliveryFee: Double
) {
    Column {
        BillItem(
            itemName = stringResource(id = R.string.sub_total),
            itemValue = "${subTotal}$"
        )
        BillItem(
            itemName = stringResource(id = R.string.delivery),
            itemValue = "${deliveryFee}$"
        )
    }
}

@Composable
private fun BillItem(
    itemName: String,
    itemValue: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = itemName,
            fontSize = 16.sp,
        )
        Text(
            text = itemValue,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
private fun OrderSectionItemPreview() {
//    OrderSectionItem()
}

@Composable
private fun OrderSectionItem(
    cartItemData: CartItemData,
    foodItem: FoodData
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 0.8.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = "${cartItemData.quantity}x",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Orange
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = foodItem.name!!,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
        }
        Text(
            text = "${cartItemData.price}$",
            fontWeight = FontWeight.Normal
        )
    }
}