package ru.anydevprojects.locatask.allTasks.presentation

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import ru.anydevprojects.locatask.networkStateMonitoring.NetworkStateCheckService
import ru.anydevprojects.locatask.root.Screens

@Composable
fun AllTasksScreen(navController: NavHostController) {
    val applicationContext = LocalContext.current.applicationContext
    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { navController.navigate(Screens.EditTask.getRouteWithArgs()) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Button(onClick = {
                val serviceIntent = Intent(applicationContext, NetworkStateCheckService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    applicationContext.startForegroundService(serviceIntent)
                }
            }) {
                Text(text = "Start service")
                Text(text = getAllSavedWifiNetworks(context = applicationContext).toString())
            }
        }
    }
}

fun getAllSavedWifiNetworks(context: Context): List<String> {
    val wifiManager = context.applicationContext.getSystemService(
        Context.WIFI_SERVICE
    ) as WifiManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        wifiManager.networkSuggestions.map {
            it.bssid.toString()
        } + wifiManager.configuredNetworks.map { it.BSSID } + wifiManager.scanResults.map { it.SSID }
    } else {
        wifiManager.configuredNetworks.map { it.BSSID }
    }
}
