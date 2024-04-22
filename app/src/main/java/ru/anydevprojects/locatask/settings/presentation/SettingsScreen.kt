package ru.anydevprojects.locatask.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import ru.anydevprojects.locatask.core.mvi.CollectEvent
import ru.anydevprojects.locatask.root.Screens
import ru.anydevprojects.locatask.settings.presentation.models.EventSettings
import ru.anydevprojects.locatask.settings.presentation.models.IntentSettings

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    CollectEvent(event = viewModel.event) { event ->
        when (event) {
            EventSettings.NavigateToGetAccessCoarseLocationPermission -> navController.navigate(
                Screens.InfoAboutPermission.route
            )
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = if (state.isAliveNetworkStateMonitoringService) {
                        "Сервис мониторинга состояния сети влючен"
                    } else {
                        "Сервис мониторинга состояния сети выключен"
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = {
                            viewModel.onIntent(
                                IntentSettings.OnRestartNetworkStateServiceBtnClick
                            )
                        }
                    ) {
                        Text(text = "Перезапустить сервис")
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = {
                            viewModel.onIntent(
                                IntentSettings.OnStopNetworkStateServiceBtnClick
                            )
                        }
                    ) {
                        Text(text = "Остановить сервис")
                    }
                }
            }
        }
    }
}
