package ru.anydevprojects.locatask.infoAboutPermission.presentation.models

sealed interface IntentInfoAboutPermission {
    data object OnDenyBtnClick : IntentInfoAboutPermission
    data object OnAllowBtnClick : IntentInfoAboutPermission
    data object IsAcceptedPermission : IntentInfoAboutPermission
    data object IsDeclinedPermission : IntentInfoAboutPermission
}
