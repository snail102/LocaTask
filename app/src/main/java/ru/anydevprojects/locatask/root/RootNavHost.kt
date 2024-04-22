package ru.anydevprojects.locatask.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.anydevprojects.locatask.editTask.presentation.EditTaskScreen
import ru.anydevprojects.locatask.infoAboutPermission.presentation.InfoAboutMonitoringScreen
import ru.anydevprojects.locatask.rootBottomNav.RootBottomScreen

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.BottomNavGraph.route) {
        composable(route = Screens.BottomNavGraph.route) {
            RootBottomScreen(rootNavController = navController)
        }

        composable(
            route = Screens.EditTask.route,
            arguments = listOf(
                navArgument(Screens.EditTask.taskIdArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(
                Screens.EditTask.taskIdArg
            ) ?: throw Exception("Empty argument taskId")
            EditTaskScreen(
                taskId = taskId,
                navController = navController
            )
        }

        composable(
            route = Screens.InfoAboutPermission.route,
            arguments = listOf(
                navArgument(Screens.InfoAboutPermission.permissionArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val permission = backStackEntry.arguments?.getString(
                Screens.InfoAboutPermission.permissionArg
            ) ?: throw Exception("Empty argument permission")
            InfoAboutMonitoringScreen(
                permission = permission
            )
        }
    }
}
