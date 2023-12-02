package com.buiducha.speedyfood.ui.screens.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.speedyfood.data.model.FoodData
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.ui.screens.shareds.HorizontalLine
import com.buiducha.speedyfood.ui.theme.Ivory
import com.buiducha.speedyfood.viewmodel.food_surf.HomeViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.CategoryViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.LocationViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.SelectedFoodViewModel

@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    selectedFoodViewModel: SelectedFoodViewModel,
    locationViewModel: LocationViewModel,
    foodViewModel: FoodViewModel,
    categoryViewModel: CategoryViewModel,
    homeViewModel: HomeViewModel = viewModel { HomeViewModel(foodViewModel) }
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val focusManager = LocalFocusManager.current

    fun onCategoryNavigate(labelId: Int) {
        navController.navigate(Screen.FoodByCategory.route)
        categoryViewModel.setLabel(labelId)
    }

    fun onDetailNavigate(foodData: FoodData) {
        navController.navigate(Screen.DetailScreen.route)
        selectedFoodViewModel.foodUpdate(foodData)
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                location = locationViewModel.geocoding.collectAsState().value,
                onSearchToggle = {
                    navController.navigate(Screen.SearchScreen.route)
                },
                onSettingsClickListener = {
                    navController.navigate(Screen.CartScreen.route)
                },
                modifier = Modifier
                    .padding(
                        all = 16.dp
                    )
            )
        },
    ) { padding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
                .background(Ivory)
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    focusManager.clearFocus()
                }
        ) {
            FoodTypesMenu(
                onCategorySelect = {label ->
                    onCategoryNavigate(label)
                },
                modifier = Modifier
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            RecommendedFoods(
                foodList = homeState.foodList,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {food ->
                onDetailNavigate(food)
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalLine(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            AllFood(
                foodList = homeState.foodList,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    )
            ) {food ->
                onDetailNavigate(food)
            }
        }
    }
}


