package ru.anydevprojects.locatask

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
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
            ActivityResultContracts.RequestPermission()
        ) { isPermissionAccepted ->
            if (isPermissionAccepted) {
                val wifiSSID = getWifiSSID(this)
                if (wifiSSID != null) {
                    println("Название Wi-Fi сети: $wifiSSID")
                } else {
                    println("Не подключены к Wi-Fi сети")
                }
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

        fineLocationPermissionContract.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getWifiSSID(context: Context): String? {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo?.type == ConnectivityManager.TYPE_WIFI) {
            val wifiManager = context.applicationContext.getSystemService(
                Context.WIFI_SERVICE
            ) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            Log.d("wifiInfo", "BSSID : ${wifiInfo.bssid}")
            return wifiInfo.ssid
        }
        return null
    }
}
