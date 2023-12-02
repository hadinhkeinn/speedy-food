package com.buiducha.speedyfood.ui.screens.settings.edit_profile_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.screens.shareds.SimpleTopBar
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.viewmodel.settings.EditProfileViewModel

@Composable
fun EditProfileScreen(
    navController: NavController,
    editProfileViewModel: EditProfileViewModel = viewModel()
) {
    val editProfileState by editProfileViewModel.editProfileState.collectAsState()
    val options = listOf(true, false)
    var expanded by remember {
        mutableStateOf(false)
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val snackBarHostState = remember { SnackbarHostState() }
    var dialogVisible by remember {
        mutableStateOf(false)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
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
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
        ) {
            if (dialogVisible) {
                EditInfoDialog {
                    navController.popBackStack()
                }
            }

            Text(
                text = stringResource(id = R.string.edit_profile),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = editProfileState.userData.name,
                onValueChange = {
                    editProfileViewModel.setUserName(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.full_name))
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = editProfileState.userData.dateOfBirth,
                onValueChange = {
                    editProfileViewModel.setDateOfBirth(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.date_of_birth))
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = editProfileState.userData.phoneNumber,
                onValueChange = {
                    editProfileViewModel.setPhoneNumber(it)
                },
                label = {
                    Text(text = stringResource(id = R.string.phone_number))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))


            Column {
                OutlinedTextField(
                    value = if (editProfileState.userData.gender) "Male" else "Female",
                    onValueChange = {  },
                    trailingIcon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.White,
                        disabledIndicatorColor = Color.Black,
                        disabledTextColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textFieldSize = coordinates.size.toSize()
                        }
                        .clickable {
                            expanded = !expanded
                        }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textFieldSize.width.toDp()})
                ) {
                    options.forEach { label ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = if (label) "Male" else "Female"
                                )
                            },
                            onClick = {
                                editProfileViewModel.setGender(label)
                                expanded = false
                            }
                        )
                    }
                }
            }
            Button(
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                onClick = {
                    editProfileViewModel.updateInfo()
                    dialogVisible = true
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
private fun EditInfoDialog(
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {

        },
        title = {
            Text(text = "User info updated successfully")
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