package ru.anydevprojects.locatask.infoAboutPermission.presentation

import androidx.lifecycle.ViewModel
import ru.anydevprojects.locatask.core.mvi.MVI
import ru.anydevprojects.locatask.core.mvi.mvi
import ru.anydevprojects.locatask.infoAboutPermission.presentation.models.EventInfoAboutPermission
import ru.anydevprojects.locatask.infoAboutPermission.presentation.models.IntentInfoAboutPermission
import ru.anydevprojects.locatask.infoAboutPermission.presentation.models.StateInfoAboutPermission
import ru.anydevprojects.locatask.permissionChecker.domain.useCases.IsAllowedPermissionUseCase

class InfoAboutPermissionViewModel(
    // private val applicationContext: Context,
    private val permission: String,
    private val isAllowedPermissionUseCase: IsAllowedPermissionUseCase
) :
    ViewModel(),
    MVI<StateInfoAboutPermission, IntentInfoAboutPermission, EventInfoAboutPermission> by mvi(
        StateInfoAboutPermission(
            isLoading = true
        )
    ) {
    init {
        checkStatusPermissions()
    }

    private fun checkStatusPermissions() {
        if (isAllowedPermissionUseCase(permission)) {
        } else {
            updateState {
                copy(
                    isLoading = false
                )
            }
        }
    }

    override fun onIntent(intent: IntentInfoAboutPermission) {
    }
}
