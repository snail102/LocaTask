package ru.anydevprojects.locatask.editTask.presentation.models

sealed interface EventEditTask {
    data object EmptyTitle : EventEditTask

    data object EmptyDescription : EventEditTask

    data object SuccessSaved : EventEditTask
}
