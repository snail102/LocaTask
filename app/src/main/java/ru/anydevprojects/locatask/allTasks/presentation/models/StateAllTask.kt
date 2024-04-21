package ru.anydevprojects.locatask.allTasks.presentation.models
data class StateAllTask(
    val isLoading: Boolean = false,
    val allTask: List<PreviewTask> = emptyList()
)
