package com.buiducha.speedyfood.ui.screens.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.speedyfood.ui.navigation.Screen
import com.buiducha.speedyfood.viewmodel.food_surf.SearchViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.SelectedFoodViewModel

@Preview
@Composable
fun SearchScreenPreview() {
//    SearchScreen()
}

@Composable
fun SearchScreen(
    navController: NavController,
    foodViewModel: FoodViewModel,
    selectedFoodViewModel: SelectedFoodViewModel,
    searchViewModel: SearchViewModel = viewModel { SearchViewModel(foodViewModel) }
) {
    val searchState by searchViewModel.searchState.collectAsState()

    Scaffold(
        topBar = {
            SearchTopBar(
                searchQuery = searchState.searchQuery,
                onSearchChange = {
                    searchViewModel.setSearchQuery(it)
                },
                onBackNav = {
                    navController.popBackStack()
                }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            SearchResults(
                suggestionList = searchState.suggestionList,
                onFoodSelect = {foodData ->
                    navController.popBackStack()
                    navController.navigate(Screen.DetailScreen.route)
                    selectedFoodViewModel.foodUpdate(foodData)
                }
            )
        }
    }
}