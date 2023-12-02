package com.buiducha.speedyfood.ui.screens.auth_screens.login_screen

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.Focused
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.theme.AuthenticTextFieldColor
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.ui.theme.TextBoldStyle
import com.buiducha.speedyfood.ui.theme.TextSemiBoldStyle
import com.buiducha.speedyfood.utils.startMainActivity
import com.buiducha.speedyfood.viewmodel.authentication.LoginViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    loginViewModel.authStateCheck(
        onUserExists = {
            startMainActivity(context)
        },
        onUserNotExists = {
            navController.navigate(Screen.AddInfoScreen.route)
        }
    )
    var emailOrPhone by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    focusManager.clearFocus()
                }
                .padding(16.dp)

        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.log_in),
                style = TextBoldStyle,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = emailOrPhone,
                onValueChange = {
                    emailOrPhone = it
                },
                colors = AuthenticTextFieldColor(),
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                placeholder = {
                    if (LocalFocusManager.current == Focused) {
                        Text(
                            text = stringResource(id = R.string.email)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                colors = AuthenticTextFieldColor(),
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Password,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (password.isNotEmpty()) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible
                                }
                        )
                    }
                },
                placeholder = {
                    if (LocalFocusManager.current == Focused) {
                        Text(
                            text = stringResource(id = R.string.password)
                        )
                    }
                },
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            FilledTonalButton(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                onClick = {
                    if (loginViewModel.isValueValid(emailOrPhone, password)) {
                        loginViewModel.userLogin(
                            activity = activity,
                            email = emailOrPhone,
                            password = password,
                            onLoginSuccess = {
                                loginViewModel.onLoginSuccess(
                                    onUserExists = {
                                        startMainActivity(context = context)
                                    },
                                    onUserNotExists = {
                                        navController.navigate(Screen.AddInfoScreen.route)
                                    }
                                )
                            },
                            onLoginFailure = { msg ->
                                scope.launch {
                                    snackBarHostState.showSnackbar(msg)
                                }
                            }
                        )
                    } else {
                        scope.launch {
                            snackBarHostState.showSnackbar("Email or password invalid")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = TextSemiBoldStyle,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    color = PrimaryColor,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.ForgotPasswordScreen.route)
                        }
                        .padding(
                            vertical = 8.dp
                        )
                )
            }
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.store_lottie)
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
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.do_not_have_account),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = stringResource(id = R.string.sign_up),
                    fontSize = 16.sp,
                    color = PrimaryColor,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                            navController.navigate(Screen.RegisterScreen.route)
                        }
                )
            }
            LottieAnimation(
                composition = composition,
                progress = {
                    progress
                }
            )

        }
    }
}