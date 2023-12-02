package com.buiducha.speedyfood.ui.screens.root_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.buiducha.speedyfood.ui.navigation.BottomBarScreen
import com.buiducha.speedyfood.ui.navigation.MainGraph
import com.buiducha.speedyfood.ui.theme.PrimaryColor
import com.buiducha.speedyfood.utils.advancedShadow
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.FoodViewModel
import com.buiducha.speedyfood.viewmodel.shared_viewmodel.LocationViewModel

@Composable
fun MainScreen(
    locationViewModel: LocationViewModel,
    foodViewModel: FoodViewModel,
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            MainGraph(
                navHostController = navController,
                locationViewModel = locationViewModel,
                foodViewModel = foodViewModel
            )
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.UserOrderScreen,
        BottomBarScreen.SettingsScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = Color.White,
            modifier = Modifier
                .advancedShadow(
                    color = Color.Gray,
                    alpha = 0.2f,
                    cornersRadius = 16.dp,
                    shadowBlurRadius = 8.dp,
                    offsetX = 1.dp,
                    offsetY = (-6).dp
                )
//                .padding(16.dp)
//                .clip(RoundedCornerShape(16))
        ) {
            screens.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = screen.title
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PrimaryColor,
                        selectedTextColor = PrimaryColor,
                        indicatorColor = Color.White
                    ),
                    modifier = Modifier
//                        .clip(CircleShape)
                        .padding(3.dp)
                )
            }
        }
    }
}