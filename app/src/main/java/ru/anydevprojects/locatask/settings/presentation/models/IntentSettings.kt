package ru.anydevprojects.locatask.settings.presentation.models

sealed interface IntentSettings {

    data object OnRestartNetworkStateServiceBtnClick : IntentSettings
    data object OnStopNetworkStateServiceBtnClick : IntentSettings
}
