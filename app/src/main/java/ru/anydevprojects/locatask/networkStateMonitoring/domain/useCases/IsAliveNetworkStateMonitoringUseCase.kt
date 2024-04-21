package ru.anydevprojects.locatask.networkStateMonitoring.domain.useCases

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import ru.anydevprojects.locatask.networkStateMonitoring.NetworkStateCheckService

class IsAliveNetworkStateMonitoringUseCase(
    private val applicationContext: Context
) {

    operator fun invoke(): Boolean {
        return isServiceRunning(applicationContext, NetworkStateCheckService::class.java)
    }

    private fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val componentName = ComponentName(context, serviceClass)
        val manager = context.packageManager
        val serviceInfo = try {
            manager.getServiceInfo(componentName, PackageManager.GET_SERVICES)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
        return serviceInfo != null
    }
}
