package ru.anydevprojects.locatask.settings.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.locatask.core.mvi.MVI
import ru.anydevprojects.locatask.core.mvi.mvi
import ru.anydevprojects.locatask.networkStateMonitoring.NetworkStateCheckService
import ru.anydevprojects.locatask.networkStateMonitoring.domain.useCases.IsAliveNetworkStateMonitoringUseCase
import ru.anydevprojects.locatask.permissionChecker.domain.useCases.IsAllowedPermissionUseCase
import ru.anydevprojects.locatask.settings.presentation.models.EventSettings
import ru.anydevprojects.locatask.settings.presentation.models.IntentSettings
import ru.anydevprojects.locatask.settings.presentation.models.StateSettings

class SettingsViewModel(
    private val isAliveNetworkStateMonitoringUseCase: IsAliveNetworkStateMonitoringUseCase,
    private val isAllowedPermissionUseCase: IsAllowedPermissionUseCase,
    private val applicationContext: Context
) :
    ViewModel(),
    MVI<StateSettings, IntentSettings, EventSettings> by mvi(
        StateSettings()
    ) {

    init {
        checkStatusNetworkStateMonitoring()
    }

    override fun onIntent(intent: IntentSettings) {
        when (intent) {
            IntentSettings.OnRestartNetworkStateServiceBtnClick -> restartService()
            IntentSettings.OnStopNetworkStateServiceBtnClick -> stopService()
        }
    }

    private fun checkStatusNetworkStateMonitoring() {
        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = false,
                    isAliveNetworkStateMonitoringService = isAliveNetworkStateMonitoringUseCase()
                )
            }
        }
    }

    private fun restartService() {
        viewModelScope.launch {
            if (isAllowedPermissionUseCase(android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                val serviceIntent = Intent(applicationContext, NetworkStateCheckService::class.java)
                applicationContext.stopService(serviceIntent)
                applicationContext.startService(serviceIntent)
            } else {
                emitEvent(
                    EventSettings.NavigateToGetAccessCoarseLocationPermission
                )
            }
        }
    }

    private fun stopService() {
        applicationContext.stopService(
            Intent(
                applicationContext,
                NetworkStateCheckService::class.java
            )
        )
    }
}
