package ru.anydevprojects.locatask.allTask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.anydevprojects.locatask.allTask.core.mvi.MVI
import ru.anydevprojects.locatask.allTask.core.mvi.mvi
import ru.anydevprojects.locatask.allTask.domain.AllTaskRepository
import ru.anydevprojects.locatask.allTask.presentation.mappers.toPreviewTask
import ru.anydevprojects.locatask.allTask.presentation.models.EventAllTask
import ru.anydevprojects.locatask.allTask.presentation.models.IntentAllTask
import ru.anydevprojects.locatask.allTask.presentation.models.StateAllTask

class AllTaskViewModel(
    private val allTaskRepository: AllTaskRepository
) : ViewModel(),
    MVI<StateAllTask, IntentAllTask, EventAllTask> by mvi(StateAllTask(isLoading = true)) {

    init {
        observeAllTask()
    }

    private fun observeAllTask() {
        allTaskRepository.allTask().onEach { allTask ->
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