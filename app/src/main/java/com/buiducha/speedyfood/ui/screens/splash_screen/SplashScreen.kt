package com.buiducha.speedyfood.ui.screens.splash_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.theme.Arima
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.ui.theme.SeoulNamSan
import com.buiducha.speedyfood.utils.startMainActivity
import com.buiducha.speedyfood.viewmodel.authentication.SplashViewModel

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = "auth_check", block = {
        splashViewModel.checkAuthState(
            onLogged = {
                splashViewModel.onLoginSuccess(
                    onUserExists = {
                        startMainActivity(context = context)
                    },
                    onUserNotExists = {
                        navController.navigate(Screen.AddInfoScreen.route)
                    }
                )
//                startMainActivity(context)
            },
            onNotLogged = {
                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.route)
            }
        )
    })

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.shipping_lottie)
        )
        val progress by animateLottieCompositionAsState(
            // pass the composition created above
            composition,

            // Iterates Forever
            iterations = LottieConstants.IterateForever,

            // pass isPlaying we created above,
            // changing isPlaying will recompose
            // Lottie and pause/play
            isPlaying = true,

            // pass speed we created above,
            // changing speed will increase Lottie
            speed = 1f,

            // this makes animation to restart
            // when paused and play
            // pass false to continue the animation
            // at which it was paused
            restartOnPlay = false
        )
        LottieAnimation(
            composition = composition,
            progress = {
                progress
            },
            modifier = Modifier
                .aspectRatio(1.2f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Speedy Food",
                fontFamily = Arima,
                color = PrimaryColor,
                fontSize = 40.sp,
                modifier = Modifier
            )
            Text(
                text = stringResource(id = R.string.app_slogan),
                fontFamily = SeoulNamSan,
                color = Color.Gray,
                fontSize = 24.sp,
                modifier = Modifier
            )
        }
    }
}