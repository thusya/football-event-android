package com.thusee.footballevent.ui.navigation

import com.thusee.footballevent.R

sealed class BottomBarNavItems(
    val route: String,
    val title: Int = 0,
    val icon: Int = 0,
) {
    data object Teams : BottomBarNavItems(
        route = NavigationScreen.Teams.route,
        title = R.string.teams_screen_name,
        icon = R.drawable.ic_home,
    )

    data object Matches : BottomBarNavItems(
        route = NavigationScreen.Matches.route,
        title = R.string.matches_screen_name,
        icon = R.drawable.ic_schedule,
    )

    data object Settings : BottomBarNavItems(
        route = NavigationScreen.Settings.route,
        title = R.string.settings_screen_name,
        icon = R.drawable.settings,
    )
}