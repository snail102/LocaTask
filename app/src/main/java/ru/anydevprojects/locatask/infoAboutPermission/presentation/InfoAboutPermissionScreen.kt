package ru.anydevprojects.locatask.infoAboutPermission.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.locatask.core.mvi.CollectEvent
import ru.anydevprojects.locatask.infoAboutPermission.presentation.models.EventInfoAboutPermission
import ru.anydevprojects.locatask.infoAboutPermission.presentation.models.IntentInfoAboutPermission
import ru.anydevprojects.locatask.ui.components.LoadingProgress

@Composable
fun InfoAboutMonitoringScreen(
    permission: String,
    viewModel: InfoAboutPermissionViewModel = koinViewModel(parameters = {
        parametersOf(permission)
    })
) {
    val fineLocationPermissionContract =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.all { it.value }) {
                viewModel.onIntent(IntentInfoAboutPermission.IsAcceptedPermission)
            } else {
                viewModel.onIntent(IntentInfoAboutPermission.IsDeclinedPermission)
            }
        }

    CollectEvent(event = viewModel.event) { event ->
        when (event) {
            EventInfoAboutPermission.ShowPermissionDialog -> fineLocationPermissionContract.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            )
        }
    }

    val state by viewModel.state.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                LoadingProgress(text = "Проверка разрешений", modifier = Modifier.fillMaxWidth())
            } else {
                Text(text = "Название разрешения")
                Text(text = "Описание для чего нужно разрешение")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Red),
                        onClick = {
                            viewModel.onIntent(IntentInfoAboutPermission.OnDenyBtnClick)
                        }
                    ) {
                        Text(text = "Запретить")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Green),
                        onClick = {
                            viewModel.onIntent(IntentInfoAboutPermission.OnAllowBtnClick)
                        }
                    ) {
                        Text(text = "Разрешить")
                    }
                }
            }
        }
    }
}
