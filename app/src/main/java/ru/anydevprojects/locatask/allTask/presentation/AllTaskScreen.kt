package ru.anydevprojects.locatask.allTask.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ru.anydevprojects.locatask.root.Screens

@Composable
fun AllTaskScreen(
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { navController.navigate(Screens.EditTask.getRouteWithArgs()) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        }
    ) {


    }
}