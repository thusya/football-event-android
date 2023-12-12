package com.thusee.footballevent.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thusee.footballevent.constants.AnimationConfig
import com.thusee.footballevent.ui.home.HomeScreen
import com.thusee.footballevent.ui.profile.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavItems.HomeScreen.route
    ) {
        composable(
            route = BottomBarNavItems.HomeScreen.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                HomeScreen()
            }
        }
        composable(
            route = BottomBarNavItems.ProfileScreen.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ProfileScreen()
            }
        }
    }
}

sealed class NavigationScreen(val route: String) {
    data object Home : NavigationScreen("Home")
    data object Profile : NavigationScreen("Profile")
}
