package ru.anydevprojects.locatask.editTask.presentation.models

data class StateEditTask(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val saving: Boolean = false
)
