package ru.anydevprojects.locatask.networkStateMonitoring

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import ru.anydevprojects.locatask.MainActivity
import ru.anydevprojects.locatask.R

class NetworkStateCheckService : Service() {

    private val channelId = "my_background_service_channel"
    private val notificationId = 101

    private val wifiReceiver = WifiReceiver()

    override fun onBind(intent: Intent?): IBinder? {
        // Этот метод вызывается, если сервис может быть связан с активностью.
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification()
        startForeground(notificationId, showNotification())
        // В этом методе выполняется ваш код для обработки фоновых операций.
        // Вы можете запустить здесь отдельный поток, чтобы выполнить задачу асинхронно.
        Log.d("NetworkStateCheckService", "Сервис запущен")
        val intentFilter = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, intentFilter)
        // Если вы хотите, чтобы сервис оставался активным, даже когда приложение закрыто,
        // используйте START_STICKY или START_REDELIVER_INTENT возвращаемые из этого метода.

        return START_STICKY
    }

    private fun showNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Мой фоновый сервис")
            .setContentText("Сервис запущен в фоновом режиме")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setOngoing(true)

        notificationBuilder.setCategory(NotificationCompat.CATEGORY_SERVICE)
        return notificationBuilder.build()
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                notificationId.toString(),
                channelId,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            getSystemService(
                NotificationManager::class.java
            ).createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Вызывается при остановке сервиса. Здесь можно освободить ресурсы.
        Log.d("NetworkStateCheckService", "Сервис остановлен")

        unregisterReceiver(wifiReceiver)

        // Удалить уведомление при остановке сервиса
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.cancel(notificationId)
    }
}
