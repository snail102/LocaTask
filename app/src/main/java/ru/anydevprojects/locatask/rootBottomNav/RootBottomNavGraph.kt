package ru.anydevprojects.locatask.rootBottomNav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.anydevprojects.locatask.allTasks.presentation.AllTasksScreen
import ru.anydevprojects.locatask.root.Screens
import ru.anydevprojects.locatask.settings.presentation.SettingsScreen

@Composable
fun RootBottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = Screens.BottomNavGraph.route,
        startDestination = Screens.AllTasks.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Screens.AllTasks.route) {
            AllTasksScreen(navController = rootNavController)
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen(navController = rootNavController)
        }
    }
}
