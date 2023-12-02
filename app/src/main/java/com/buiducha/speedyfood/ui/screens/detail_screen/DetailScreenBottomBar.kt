package com.buiducha.speedyfood.ui.screens.detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
import com.buiducha.speedyfood.ui.theme.Gold
import com.buiducha.speedyfood.ui.theme.PrimaryColor

@Preview
@Composable
fun DetailBottomBarPreview() {
    DetailScreenBottomBar(
        36.0
    ) {

    }
}

@Composable
fun DetailScreenBottomBar(
    totalPrice: Double,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.total_price),
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.AttachMoney,
                    contentDescription = stringResource(id = R.string.image_des),
                    tint = Gold
                )
                Text(
                    text = totalPrice.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        FilledTonalButton(
            onClick = {
                onAddToCart()
            },
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                bottomStart = 20.dp,
                bottomEnd = 18.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
//                contentColor = Color.White
            ),
            contentPadding = PaddingValues(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = stringResource(id = R.string.image_des),
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.add_to_cart),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
//        TextButton(
//            onClick = {
//
//            },
//            modifier = Modifier
//                .background(
//                    color = Color.Black,
//                    shape = RoundedCornerShape(
//                        topEnd = 16.dp,
//                        bottomStart = 20.dp,
//                        bottomEnd = 18.dp
//                    )
//                )
//                .padding(
//                    vertical = 4.dp,
//                    horizontal = 8.dp
//                )
//        ) {
//            Icon(
//                imageVector = Icons.Filled.ShoppingCart,
//                contentDescription = stringResource(id = R.string.image_des),
//                tint = Color.White,
//                modifier = Modifier
//                    .size(16.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = stringResource(id = R.string.add_to_cart),
//                color = Color.White,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Normal
//            )
//        }
    }
}
