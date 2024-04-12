package ru.anydevprojects.locatask.editTask.presentation.models

sealed interface IntentEditTask {

    data class OnChangeTitle(val newValue: String) : IntentEditTask

    data class OnChangeDescription(val newValue: String) : IntentEditTask

    data object OnClickSaveBtn : IntentEditTask
}