package ru.anydevprojects.locatask.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.anydevprojects.locatask.allTask.presentation.AllTaskScreen
import ru.anydevprojects.locatask.editTask.presentation.EditTaskScreen

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.AllTask.route) {
        composable(route = Screens.AllTask.route) {
            AllTaskScreen(navController = navController)
        }
        composable(route = Screens.EditTask.route,   arguments = listOf(
            navArgument(Screens.EditTask.taskIdArg) {
                type = NavType.StringType
            }
        )) {backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(
                Screens.EditTask.taskIdArg
            ) ?: throw Exception("Empty argument taskId")
            EditTaskScreen(
                taskId = taskId,
                navController = navController
            )
        }
    }
}