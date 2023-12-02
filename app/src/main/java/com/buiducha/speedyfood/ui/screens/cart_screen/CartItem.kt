package com.buiducha.speedyfood.ui.screens.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.data.model.CartItemData
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.theme.DarkGray
import com.buiducha.speedyfood.ui.theme.Orange
import com.buiducha.speedyfood.ui.theme.TextSemiBoldStyle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview
@Composable
fun CartItemPreview() {
//    CartItem(
//        food = ItemFood(
//            imageId = R.drawable.beef_steak,
//            label = "Beef steak",
//            price = "36"
//        )
//    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItem(
    cartItemData: CartItemData,
    foodData: FoodData,
    modifier: Modifier = Modifier,
    onAddQuantity: () -> Unit,
    onSubQuantity: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 0.5.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                painter = painterResource(id = food.imageId),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(80.dp)
//            )
            GlideImage(
                model = foodData.imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = foodData.name!!,
                    style = TextSemiBoldStyle,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = "$${cartItemData.price}",
                        style = TextSemiBoldStyle,
                        fontSize = 17.sp,
                        color = Orange
                    )
                    Text(
                        text = " x ",
                        style = TextSemiBoldStyle,
                        fontSize = 17.sp,
                        color = Orange
                    )
                    Text(
                        text = "${cartItemData.quantity}",
                        style = TextSemiBoldStyle,
                        fontSize = 17.sp,
                        color = Orange
                    )
                }
            }
        }
        ChangeQuantityButton(
            onAddQuantity = onAddQuantity,
            onSubQuantity = onSubQuantity
        )
    }
}

@Preview
@Composable
fun ChangeQuantityButtonPreview() {
//    ChangeQuantityButton()
}

@Composable
fun ChangeQuantityButton(
    modifier: Modifier = Modifier,
    onAddQuantity: () -> Unit,
    onSubQuantity: () -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .padding(
                    end = 16.dp
                )
                .background(
                    Color.Black,
                    RoundedCornerShape(6.dp)
                )
                .width(32.dp)
        ) {
            Button(
                onClick = {
                    onAddQuantity()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGray
                ),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
            Button(
                onClick = {
                    onSubQuantity()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(
                    bottomStart = 6.dp,
                    bottomEnd = 6.dp
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = null
                )
            }
        }
    }
}