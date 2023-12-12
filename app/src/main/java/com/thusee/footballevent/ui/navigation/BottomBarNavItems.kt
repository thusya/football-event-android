package com.thusee.footballevent.ui.navigation

import com.thusee.footballevent.R

sealed class BottomBarNavItems(
    val route: String,
    val title: Int = 0,
    val icon: Int = 0,
) {
    data object HomeScreen : BottomBarNavItems(
        route = NavigationScreen.Home.route,
        title = R.string.home_screen_name,
        icon = R.drawable.ic_home,
    )

    data object ProfileScreen : BottomBarNavItems(
        route = NavigationScreen.Profile.route,
        title = R.string.profile_screen_name,
        icon = R.drawable.ic_profile,
    )
}