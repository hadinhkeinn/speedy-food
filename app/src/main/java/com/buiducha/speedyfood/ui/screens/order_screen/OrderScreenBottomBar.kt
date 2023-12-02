package com.buiducha.speedyfood.ui.screens.order_screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.theme.PrimaryColor

@Preview
@Composable
fun OrderBottomBarPreview() {
    OrderScreenBottomBar(total = 123.0) {}
}

@Composable
fun OrderScreenBottomBar(
    total: Double,
    onPlaceOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.total),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Text(
                text = "${total}$",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                onPlaceOrder()
            },
            shape = RoundedCornerShape(8),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.place_order),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        vertical = 4.dp
                    )
            )
        }
    }
}