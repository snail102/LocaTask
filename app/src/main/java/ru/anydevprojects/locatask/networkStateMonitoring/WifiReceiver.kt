package ru.anydevprojects.locatask.networkStateMonitoring

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import ru.anydevprojects.locatask.notificationControl.NotificationControl

class WifiReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            val networkInfo: NetworkInfo? = intent?.getParcelableExtra(
                WifiManager.EXTRA_NETWORK_INFO
            )
            val isConnected: Boolean = networkInfo?.isConnected ?: false
            val isWifi: Boolean = networkInfo?.type == ConnectivityManager.TYPE_WIFI

            if (isConnected && isWifi && context != null) {
                Log.d("WifiReceiver", "Подключение к Wi-Fi сети")
                val wifiSSID = getWifiSSID(context)
                if (wifiSSID != null) {
                    NotificationControl.showNotification(
                        title = "Подключился к wifi",
                        text = wifiSSID
                    )
                    println("Название Wi-Fi сети: $wifiSSID")
                } else {
                    println("Не подключены к Wi-Fi сети")
                }
                // Подключение к Wi-Fi сети
                // Здесь вы можете выполнить необходимые действия
            }
        }
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
