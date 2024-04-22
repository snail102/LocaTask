package ru.anydevprojects.locatask.settings.presentation.models

sealed interface EventSettings {
    data object NavigateToGetAccessCoarseLocationPermission : EventSettings
}
