package ru.anydevprojects.locatask.rootBottomNav

import ru.anydevprojects.locatask.root.Screens

sealed class BottomBarScreen(val route: String) {

    data object AllTask : BottomBarScreen(
        route = Screens.AllTasks.route
    )

    data object Settings : BottomBarScreen(
        route = Screens.Settings.route
    )
}
