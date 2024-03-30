package com.thusee.footballevent.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.thusee.footballevent.constants.AnimationConfig
import com.thusee.footballevent.ui.matches.MatchesScreen
import com.thusee.footballevent.ui.teams.TeamsScreen
import com.thusee.footballevent.ui.teamsdetails.TeamMatchesDetailsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM_BAR,
        startDestination = BottomBarNavItems.Teams.route
    ) {
        composable(
            route = BottomBarNavItems.Teams.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                TeamsScreen(navController = navController)
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

        detailsNavGraph(navController)
    }
}

object Graph {
    const val BOTTOM_BAR = "bottom_bar_graph"
    const val TEAMS = "teams_graph"
}

internal const val TEAM_ID = "teamId"
internal const val TEAM_NAME = "teamName"
fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graph.TEAMS,
        startDestination = DetailsScreen.TeamMatchesDetails.route
    ) {
        composable(
            route = DetailsScreen.TeamMatchesDetails.route,
            arguments = listOf(
                navArgument(TEAM_ID) { type = NavType.StringType },
                navArgument(TEAM_NAME) { type = NavType.StringType },
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            val teamName = backStackEntry.arguments?.getString(TEAM_NAME) ?: ""

            TeamMatchesDetailsScreen(
                teamName = teamName,
                navController = navController
            )
        }
    }
}

sealed class NavigationScreen(val route: String) {
    data object Teams : NavigationScreen("Teams")
    data object Matches : NavigationScreen("Matches")
    data object Settings : NavigationScreen("Settings")
}

sealed class DetailsScreen(val route: String) {
    data object TeamMatchesDetails :
        DetailsScreen(route = "team_matches_details/{$TEAM_ID}/{$TEAM_NAME}")
}
