package ru.anydevprojects.locatask.permissionChecker.domain.useCases

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class IsAllowedPermissionUseCase(
    private val applicationContext: Context
) {
    operator fun invoke(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            applicationContext,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}
