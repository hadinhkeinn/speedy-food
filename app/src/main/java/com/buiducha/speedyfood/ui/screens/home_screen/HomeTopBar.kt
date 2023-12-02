package com.buiducha.speedyfood.ui.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.ui.theme.TextLightStyle

@Preview
@Composable
fun HomeTopBarPreview() {
//    HomeTopBar(location = "New York, NYC")
}

@Composable
fun HomeTopBar(
    location: String,
    modifier: Modifier = Modifier,
    onSettingsClickListener: () -> Unit,
    onSearchToggle: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        FoodSearchView(
            location = location,
            onSearchToggle = onSearchToggle,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        CartButton(
            onCartClickListener = onSettingsClickListener
        )
    }
}

@Composable
fun FoodSearchView(
    location: String,
    onSearchToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .clickable {
                onSearchToggle()
            }
            .border(
                0.8.dp,
                Color.LightGray,
                RoundedCornerShape(30.dp)
            )
            .height(48.dp)
            .padding(horizontal = 8.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.image_des)
            )
            Text(
                text = "Search",
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .width(0.5.dp)
                    .fillMaxHeight(0.4f)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = stringResource(id = R.string.image_des)
            )
            Text(
                text = location,
                style = TextLightStyle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun CartButton(
    onCartClickListener: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(shape = CircleShape)
            .clickable {
                onCartClickListener()
            }
            .background(
                color = PrimaryColor,
            )
            .size(48.dp)

    ) {
        Icon(
//            painter = painterResource(id = R.drawable.icon_settings),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = stringResource(id = R.string.image_des),
            modifier = Modifier
                .padding(16.dp),
            tint = Color.White
        )
    }
}