package ru.anydevprojects.locatask.rootBottomNav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootBottomScreen(
    navController: NavHostController = rememberNavController(),
    rootNavController: NavHostController
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Column {
            RootBottomNavGraph(navController = navController, rootNavController = rootNavController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.AllTask,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    TabView(
        screens = screens,
        currentDestination = currentDestination,
        navController = navController
    )
}

@Composable
fun TabView(
    screens: List<BottomBarScreen>,
    navController: NavController,
    currentDestination: NavDestination?
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    NavigationBar {
        screens.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == tabBarItem.route
                } == true,
                onClick = {
                    navController.navigate(tabBarItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    val icon = when (tabBarItem) {
                        BottomBarScreen.AllTask -> Icons.Default.Home
                        BottomBarScreen.Settings -> Icons.Default.Person
                    }
                    Icon(imageVector = icon, contentDescription = null)
                },
                label = {
                    val title = when (tabBarItem) {
                        BottomBarScreen.AllTask -> "Задачи"
                        BottomBarScreen.Settings -> "Настройки"
                    }
                    Text(title)
                }
            )
        }
    }
}
