package ru.anydevprojects.locatask.allTasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.anydevprojects.locatask.allTasks.domain.AllTasksRepository
import ru.anydevprojects.locatask.allTasks.presentation.mappers.toPreviewTask
import ru.anydevprojects.locatask.allTasks.presentation.models.EventAllTask
import ru.anydevprojects.locatask.allTasks.presentation.models.IntentAllTask
import ru.anydevprojects.locatask.allTasks.presentation.models.StateAllTask
import ru.anydevprojects.locatask.core.mvi.MVI
import ru.anydevprojects.locatask.core.mvi.mvi

class AllTasksViewModel(
    private val allTasksRepository: AllTasksRepository
) : ViewModel(),
    MVI<StateAllTask, IntentAllTask, EventAllTask> by mvi(StateAllTask(isLoading = true)) {

    init {
        observeAllTask()
    }

    private fun observeAllTask() {
        allTasksRepository.allTasks().onEach { allTask ->
            val allPreviewTask = allTask.map { task ->
                task.toPreviewTask()
            }
            updateState {
                copy(
                    isLoading = false,
                    allTask = allPreviewTask
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: IntentAllTask) {
    }
}
