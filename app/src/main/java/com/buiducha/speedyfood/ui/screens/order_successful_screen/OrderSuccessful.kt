package com.buiducha.speedyfood.ui.screens.order_successful_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Preview
@Composable
fun OrderSuccessfulPreview() {
    OrderSuccessful(navController = rememberNavController())
}

@Composable
fun OrderSuccessful(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.order_successful),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = PrimaryColor
        )
    }
    LaunchedEffect(key1 = "my_key", block = {
        delay(2000)
        navController.navigate(Screen.HomeScreen.route) {
            // Specify the popUpTo attribute to remove all screens up to "home" destination
            popUpTo(navController.graph.startDestinationId) {
                // Use inclusive = true to also remove the "home" destination.
                inclusive = true
            }
            // Specify the behavior when navigating to "home".
            launchSingleTop = true
        }
    })
}