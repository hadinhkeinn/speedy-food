package com.buiducha.speedyfood.ui.screens.settings.change_password_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.data.repository.FireBaseRepository.Companion.OLD_PASSWORD_INVALID
import com.buiducha.speedyfood.data.repository.FireBaseRepository.Companion.PASSWORD_CHANGE_FAILURE
import com.buiducha.speedyfood.ui.screens.shareds.SimpleTopBar
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.viewmodel.settings.ChangePasswordViewModel

@Composable
fun ChangePasswordScreen(
    navController: NavController,
    changePasswordViewModel: ChangePasswordViewModel = viewModel()
) {
    val changePasswordState = changePasswordViewModel.changePasswordState.collectAsState()
    var dialogVisible by remember {
        mutableStateOf(false)
    }
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            SimpleTopBar(
                onBackListener = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (dialogVisible) {
                ChangePasswordDialog {
                    navController.popBackStack()
                }
            }
            Text(
                text = stringResource(id = R.string.change_password),
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(
                value = changePasswordState.value.oldPassword,
                onChangeValue = {
                    changePasswordViewModel.setOldPassword(it)
                },
                label = stringResource(id = R.string.old_password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordTextField(
                value = changePasswordState.value.newPassword,
                onChangeValue = {
                    changePasswordViewModel.setNewPassword(it)
                },
                label = stringResource(id = R.string.new_password)
            )
            PasswordTextField(
                value = changePasswordState.value.confirmPassword,
                onChangeValue = {
                    changePasswordViewModel.setConfirmPassword(it)
                },
                label = stringResource(id = R.string.confirm_password)
            )
            Button(
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                onClick = {
                    changePasswordViewModel.changePassword(
                        onChangePasswordSuccess = {
                            dialogVisible = true
                        },
                        onChangePasswordFailure = { errorCode ->
                            when(errorCode) {
                                OLD_PASSWORD_INVALID -> {

                                }
                                PASSWORD_CHANGE_FAILURE -> {

                                }
                            }
                        }
                    )
                },
                modifier = Modifier
                    .padding(
                        vertical = 32.dp
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }
        }
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    label: String,
) {
    var isValueVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            onChangeValue(it)
        },
        label = {
            Text(text = label)
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isValueVisible = !isValueVisible
                }
            ) {
                if (value.isNotEmpty()) {
                    Icon(
                        imageVector = if (isValueVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                    )
                }

            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (isValueVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun ChangePasswordDialog(
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {

        },
        title = {
            Text(text = "Password changed successfully")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(text = "Ok")
            }
        },
    )
}