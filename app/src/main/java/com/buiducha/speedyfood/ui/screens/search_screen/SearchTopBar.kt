package com.buiducha.speedyfood.ui.screens.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.speedyfood.ui.theme.Shade
import com.buiducha.speedyfood.utils.advancedShadow

@Preview
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(
        searchQuery = "",
        onSearchChange = {

        },
        onBackNav = {

        }
    )
}

@Composable
fun SearchTopBar(
    searchQuery: String,
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit,
    onBackNav: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .advancedShadow(
                color = Color.Black,
                shadowBlurRadius = 4.dp,
                alpha = 0.3f,
                offsetY = 2.dp,
            )
            .background(Color.White)
            .padding(
                vertical = 4.dp
            )
    ) {
        IconButton(
            onClick = {
                onBackNav()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        remember { MutableInteractionSource() }

        BasicTextField(
            value = searchQuery,
            onValueChange = { newText ->
                onSearchChange(newText)
            },
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(
                            end = 12.dp
                        ) // margin left and right
                        .fillMaxWidth()
                        .background(
                            color = Shade,
                            shape = RoundedCornerShape(12)
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ), // inner padding
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.width(width = 8.dp))
                        innerTextField()
                    }
//                    if (value.isNotEmpty()) {
//                        Icon(
//                            imageVector = Icons.Default.Cancel,
//                            contentDescription = null,
//                            tint = Color.DarkGray
//                        )
//                    }
                }
            },
        )
//        val interactionSource = remember { MutableInteractionSource() }
//        BasicTextField(
//            value = value,
//            modifier = modifier,
//            onValueChange = {
//                value = it
//            },
//            enabled = true,
//            readOnly = false,
//            singleLine = true,
//            decorationBox = @Composable { innerTextField ->
//                // places leading icon, text field with label and placeholder, trailing icon
//                TextFieldDefaults.DecorationBox(
//                    value = value,
//                    innerTextField = innerTextField,
//                    placeholder = { Text(text = "")},
//                    leadingIcon = {
//                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
//                    },
//                    trailingIcon = {
////                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
//                    },
//                    singleLine = true,
//                    enabled = true,
//                    visualTransformation = VisualTransformation.None,
//                    interactionSource = interactionSource,
//                    colors = TextFieldDefaults.colors(
//                        unfocusedContainerColor = Shade,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedContainerColor = Shade
//                    ),
//                    contentPadding = PaddingValues(0.dp)
//                )
//            }
//        )
    }
}