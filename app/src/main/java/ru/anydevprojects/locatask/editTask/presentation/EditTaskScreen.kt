package ru.anydevprojects.locatask.editTask.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.locatask.core.mvi.CollectEvent
import ru.anydevprojects.locatask.editTask.presentation.models.EventEditTask
import ru.anydevprojects.locatask.editTask.presentation.models.IntentEditTask

@Composable
fun EditTaskScreen(
    taskId: String,
    navController: NavHostController,
    viewModel: EditTaskViewModel =
        koinViewModel(
            parameters = {
                parametersOf(taskId)
            }
        )
) {
    val state by viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    CollectEvent(event = viewModel.event) { event: EventEditTask ->
        when (event) {
            EventEditTask.EmptyDescription -> {
                scope.launch {
                    snackbarHostState.showSnackbar(message = "Описание не может быть пустым")
                }
            }

            EventEditTask.EmptyTitle -> {
                scope.launch {
                    snackbarHostState.showSnackbar(message = "Название не может быть пустым")
                }
            }

            EventEditTask.SuccessSaved -> {
                navController.popBackStack()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (!state.isLoading) {
                SmallFloatingActionButton(onClick = {
                    if (!state.saving) {
                        viewModel.onIntent(IntentEditTask.OnClickSaveBtn)
                    }
                }) {
                    if (state.saving) {
                        CircularProgressIndicator()
                    } else {
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                }
            }
        }
    ) {
        if (state.isLoading) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.saving,
                    value = state.title,
                    onValueChange = {
                        viewModel.onIntent(IntentEditTask.OnChangeTitle(it))
                    }
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !state.saving,
                    value = state.description,
                    onValueChange = {
                        viewModel.onIntent(IntentEditTask.OnChangeDescription(it))
                    }
                )
            }
        }
    }
}
