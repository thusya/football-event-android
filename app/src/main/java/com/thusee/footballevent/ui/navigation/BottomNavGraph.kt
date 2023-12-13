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
import com.thusee.footballevent.ui.teams.TeamsScreen
import com.thusee.footballevent.ui.matches.MatchesScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavItems.Teams.route
    ) {
        composable(
            route = BottomBarNavItems.Teams.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                TeamsScreen()
            }
        }
        composable(
            route = BottomBarNavItems.Matches.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                MatchesScreen()
            }
        }
    }
}

sealed class NavigationScreen(val route: String) {
    data object Teams : NavigationScreen("Teams")
    data object Matches : NavigationScreen("Matches")
}
