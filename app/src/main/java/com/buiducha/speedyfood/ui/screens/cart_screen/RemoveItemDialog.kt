package com.buiducha.speedyfood.ui.screens.cart_screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.R
import com.buiducha.speedyfood.ui.theme.Orange

@Preview
@Composable
fun DialogPreview() {
    RemoveItemDialog(
        onDismissRequest = { /*TODO*/ },
        onConfirmation = { /*TODO*/ },
        dialogTitle = stringResource(id = R.string.remove_item),
    )
}

@Composable
fun RemoveItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                fontSize = 22.sp
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.yes),
                    fontSize = 18.sp,
                    color = Orange
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.no),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    )
}