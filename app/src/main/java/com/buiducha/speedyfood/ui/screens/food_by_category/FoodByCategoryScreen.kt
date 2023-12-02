package com.buiducha.speedyfood.ui.screens.food_by_category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.screens.shareds.FoodItemHor
import com.buiducha.speedyfood.ui.screens.shareds.SimpleTopBar
import com.buiducha.speedyfood.viewmodel.food_surf.FoodByCategoryViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.CategoryViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.SelectedFoodViewModel

@Preview
@Composable
fun FoodByCategoryPreview() {
//    FoodByCategory(rememberNavController())
}

@Composable
fun FoodByCategoryScreen(
    navController: NavController,
    foodViewModel: FoodViewModel,
    selectedFoodViewModel: SelectedFoodViewModel,
    categoryViewModel: CategoryViewModel,
    foodByCategoryViewModel: FoodByCategoryViewModel = viewModel{ FoodByCategoryViewModel(foodViewModel, categoryViewModel) },
) {
    val foodByCategoryState by foodByCategoryViewModel.foodByCategoryState.collectAsState()
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
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = foodByCategoryState.categoryLabel),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(foodByCategoryState.foodList) {food ->
                    FoodItemHor(
                        foodData = food,
                        onFoodSelect = {foodData ->
                            navController.popBackStack()
                            navController.navigate(Screen.DetailScreen.route)
                            selectedFoodViewModel.foodUpdate(foodData)
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp
                            )
                    )
                }
            }
        }
    }
}