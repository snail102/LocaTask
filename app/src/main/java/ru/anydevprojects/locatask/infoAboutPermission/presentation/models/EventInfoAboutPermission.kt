package ru.anydevprojects.locatask.infoAboutPermission.presentation.models

sealed interface EventInfoAboutPermission {
    data object ShowPermissionDialog : EventInfoAboutPermission
}
