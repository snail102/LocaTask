package ru.anydevprojects.locatask.allTask.presentation.models
data class StateAllTask(
    val isLoading: Boolean = false,
    val allTask: List<PreviewTask> = emptyList()
)