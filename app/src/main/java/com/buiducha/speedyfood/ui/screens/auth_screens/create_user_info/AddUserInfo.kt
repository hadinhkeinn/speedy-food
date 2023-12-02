package com.buiducha.speedyfood.ui.screens.auth_screens.create_user_info

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.utils.startMainActivity
import com.buiducha.speedyfood.viewmodel.authentication.AddInfoViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun AddUserInfoPreview() {
    AddUserInfo(rememberNavController())
}
@Composable
fun AddUserInfo(
    navController: NavController,
    addInfoViewModel: AddInfoViewModel = viewModel()
) {
    val addInfoState = addInfoViewModel.addInfoState.collectAsState()
    val options = listOf(true, false)
    var expanded by remember {
        mutableStateOf(false)
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

   Scaffold(
       snackbarHost = {
           SnackbarHost(hostState = snackBarHostState)
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
           Text(
               text = stringResource(id = R.string.fill_information_guide),
               fontWeight = FontWeight.Bold,
               fontSize = 32.sp,
               maxLines = 2,
           )
           Spacer(modifier = Modifier.height(24.dp))
           OutlinedTextField(
               value = addInfoState.value.fullName,
               onValueChange = {
                   addInfoViewModel.setFullName(it)
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
               value = addInfoState.value.dateOfBirth,
               onValueChange = {
                   addInfoViewModel.setDateOfBirth(it)
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
               value = addInfoState.value.phoneNumber,
               onValueChange = {
                   addInfoViewModel.setPhoneNumber(it)
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
                   value = if (addInfoState.value.gender) "Male" else "Female",
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
                       disabledTextColor = Color.Black
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
                               addInfoViewModel.setGender(label)
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
                   addInfoViewModel.addUserInfo(
                       onAddSuccess = {
                           startMainActivity(context)
                       },
                       onAddFailure = {
                           scope.launch {
                               snackBarHostState.showSnackbar("Can't add user info")
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