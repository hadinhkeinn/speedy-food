package com.buiducha.speedyfood.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.speedyfood.ui.screens.cart_screen.CartScreen
import com.buiducha.speedyfood.ui.screens.detail_screen.DetailScreen
import com.buiducha.speedyfood.ui.screens.food_by_category.FoodByCategoryScreen
import com.buiducha.speedyfood.ui.screens.home_screen.HomeScreen
import com.buiducha.speedyfood.ui.screens.order_screen.OrderScreen
import com.buiducha.speedyfood.ui.screens.order_successful_screen.OrderSuccessful
import com.buiducha.speedyfood.ui.screens.search_screen.SearchScreen
import com.buiducha.speedyfood.ui.screens.settings.change_password_screen.ChangePasswordScreen
import com.buiducha.speedyfood.ui.screens.settings.edit_profile_screen.EditProfileScreen
import com.buiducha.speedyfood.ui.screens.settings.settings_screen.SettingsScreen
import com.buiducha.speedyfood.ui.screens.user_order_screen.UserOrderScreen
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.CategoryViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.LocationViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.SelectedFoodViewModel

@Composable
fun MainGraph(
    navHostController: NavHostController,
    locationViewModel: LocationViewModel,
    foodViewModel: FoodViewModel
) {
    val selectedFoodViewModel: SelectedFoodViewModel = viewModel()
    val categoryViewModel: CategoryViewModel = viewModel()
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.HomeScreen.route
    ) {
        composable(
            route = BottomBarScreen.HomeScreen.route
        ) {
            HomeScreen(
                navController = navHostController,
                selectedFoodViewModel = selectedFoodViewModel,
                locationViewModel = locationViewModel,
                foodViewModel = foodViewModel,
                categoryViewModel = categoryViewModel
            )
        }
        composable(
            route = Screen.DetailScreen.route
        ) {
            DetailScreen(
                navHostController = navHostController,
                foodViewModel = selectedFoodViewModel
            )
        }
        composable(
            route = Screen.FoodByCategory.route
        ) {
            FoodByCategoryScreen(
                navController = navHostController,
                foodViewModel = foodViewModel,
                selectedFoodViewModel = selectedFoodViewModel,
                categoryViewModel = categoryViewModel
            )
        }
        composable(
            route = Screen.CartScreen.route
        ) {
            CartScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(
                navController = navHostController,
                foodViewModel = foodViewModel,
                selectedFoodViewModel = selectedFoodViewModel
            )
        }
        composable(
            route = Screen.OrderScreen.route
        ) {
            OrderScreen(
                locationViewModel = locationViewModel,
                navController = navHostController
            )
        }
        composable(
            route = Screen.OrderSuccessfulScreen.route
        ) {
            OrderSuccessful(
                navController = navHostController
            )
        }
        composable(
            route = BottomBarScreen.UserOrderScreen.route
        ) {
            UserOrderScreen()
        }
        composable(
            route = BottomBarScreen.SettingsScreen.route
        ) {
            SettingsScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.ChangePasswordScreen.route
        ) {
            ChangePasswordScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.EditProfileScreen.route
        ) {
            EditProfileScreen(
                navController = navHostController
            )
        }

    }
}