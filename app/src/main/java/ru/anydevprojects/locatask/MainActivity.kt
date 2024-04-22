package ru.anydevprojects.locatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ru.anydevprojects.locatask.root.RootNavHost
import ru.anydevprojects.locatask.ui.theme.LocaTaskTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LocaTaskTheme {
                RootNavHost(navController = rememberNavController())
            }
        }
    }
}
