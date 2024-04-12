package ru.anydevprojects.locatask.editTask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.locatask.allTask.core.mvi.MVI
import ru.anydevprojects.locatask.allTask.core.mvi.mvi
import ru.anydevprojects.locatask.editTask.domain.EditTaskRepository
import ru.anydevprojects.locatask.editTask.presentation.models.EventEditTask
import ru.anydevprojects.locatask.editTask.presentation.models.IntentEditTask
import ru.anydevprojects.locatask.editTask.presentation.models.StateEditTask
import java.lang.Thread.State

class EditTaskViewModel(
    private val taskId: String,
    private val editTaskRepository: EditTaskRepository
) : ViewModel(), MVI<StateEditTask, IntentEditTask, EventEditTask> by mvi(
    StateEditTask()
) {

    init {
        loadTaskIfEditMode()
    }

    private fun loadTaskIfEditMode() {
        if (taskId.isEmpty()) {
            return
        }

        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true
                )
            }
            val task = editTaskRepository.getTask(taskId = taskId)
            updateState {
                copy(
                    isLoading = false,
                    title = task.title,
                    description = task.description
                )
            }
        }
    }

    override fun onIntent(intent: IntentEditTask) {
        when (intent) {
            is IntentEditTask.OnChangeDescription -> updateDescription(intent.newValue)
            is IntentEditTask.OnChangeTitle -> updateTitle(intent.newValue)
            IntentEditTask.OnClickSaveBtn -> saveTask()
        }
    }


    private fun updateTitle(title: String) {
        updateState {
            copy(title = title)
        }
    }


    private fun updateDescription(description: String) {
        updateState {
            copy(description = description)
        }
    }

    private fun saveTask() {
        viewModelScope.launch {

            if (lastState.title.isEmpty()) {
                emitEvent(EventEditTask.EmptyTitle)
                return@launch
            }

            if (lastState.description.isEmpty()) {
                emitEvent(EventEditTask.EmptyDescription)
                return@launch
            }

            updateState {
                copy(
                    saving = true
                )
            }

            if (taskId.isEmpty()) {
                editTaskRepository.saveTask(
                    title = lastState.title,
                    description = lastState.description
                )
            } else {
                editTaskRepository.updateTask(
                    id = taskId,
                    title = lastState.title,
                    description = lastState.description
                )
            }

            updateState {
                copy(
                    saving = false
                )
            }

            emitEvent(
                EventEditTask.SuccessSaved
            )
        }
    }
}