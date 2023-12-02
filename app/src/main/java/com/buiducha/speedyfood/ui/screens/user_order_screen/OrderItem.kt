package com.buiducha.speedyfood.ui.screens.user_order_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.model.CartItemData
import com.buiducha.speedyfood.data.model.OrderData
import com.buiducha.speedyfood.ui.screens.shareds.HorizontalLine
import com.buiducha.speedyfood.ui.theme.DarkGreen
import com.buiducha.speedyfood.ui.theme.LightGray
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.ui.theme.Shade
import com.buiducha.speedyfood.ui.theme.VeryLightGray

@Composable
fun OrderItem(
    orderItem: OrderData,
    onItemFeedback: (String) -> Unit,
    isReceivedFeedback: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .background(LightGray)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_id) + " " + orderItem.orderId,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
        Text(
            text = orderItem.orderDate,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(
                    vertical = 4.dp
                )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${orderItem.totalPrice}$",
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (orderItem.orderStatus) Icons.Default.CheckCircle else Icons.Default.HourglassEmpty,
                    contentDescription = null,
                    tint = if (orderItem.orderStatus) DarkGreen else Color.Red
                )
                Text(
                    text = stringResource(
                        id = if (orderItem.orderStatus) R.string.accomplished else R.string.processing
                    ),
                    color = if (orderItem.orderStatus) DarkGreen else Color.Red
                )
            }
        }
        ExpandSection(
            orderItem = orderItem,
            onItemFeedback = onItemFeedback,
            isReceivedFeedback = isReceivedFeedback
        )
    }
}

@Composable
private fun ExpandSection(
    orderItem: OrderData,
    isReceivedFeedback: Boolean,
    onItemFeedback: (String) -> Unit
) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    Column {
        HorizontalLine(
            weight = 0.2,
            modifier = Modifier
                .padding(
                    vertical = 8.dp
                )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpand = !isExpand
                }
        ) {
            Text(
                text = stringResource(id = R.string.order),
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        if (isExpand) {
            DeliveryAddress(
                deliveryAddress = orderItem.address
            )
            ItemList(
                itemList = orderItem.itemList
            )
            if (orderItem.orderStatus) {
                Spacer(modifier = Modifier.height(8.dp))
                ContactSection(
                    onItemFeedback = {
                        onItemFeedback(orderItem.orderId)
                    },
                    isReceivedFeedback = isReceivedFeedback
                )
            }
        }
    }
}

@Composable
private fun ItemList(
    itemList: List<CartItemData>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
//        Text(
//            text = stringResource(id = R.string.item_list)
//        )
        Spacer(modifier = Modifier.height(8.dp))
        itemList.forEach {item ->
            SingleItem(item = item)
        }
    }
}

@Composable
private fun SingleItem(
    item: CartItemData
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.quantity}x",
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.foodId
            )
        }
        Text(
            text = "${item.price} $",
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DeliveryAddress(
    deliveryAddress: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                vertical = 4.dp
            )
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = Color.Red
        )
        Column {
            Text(
                text = stringResource(id = R.string.delivery_address),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = deliveryAddress,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

    }
}

@Preview
@Composable
fun ContactSectionPreview() {
    ContactSection(false) {}
}

@Composable
fun ContactSection(
    isReceivedFeedback: Boolean,
    onItemFeedback: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8))
                .background(LightGray)
                .clickable { }
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = DarkGreen
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                onItemFeedback()
            },
            shape = RoundedCornerShape(8),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            enabled = !isReceivedFeedback,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.feedback),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                    )
            )
        }
    }
}