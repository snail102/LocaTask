package ru.anydevprojects.locatask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import ru.anydevprojects.locatask.root.RootNavHost
import ru.anydevprojects.locatask.ui.theme.LocaTaskTheme

class MainActivity : ComponentActivity() {

    private val fineLocationPermissionContract =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.all { it.value }) {

                Toast.makeText(this, "Permission is accepted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission is declined", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LocaTaskTheme {
                RootNavHost(navController = rememberNavController())
            }
        }

        fineLocationPermissionContract.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.POST_NOTIFICATIONS
            )
        )
    }
}
