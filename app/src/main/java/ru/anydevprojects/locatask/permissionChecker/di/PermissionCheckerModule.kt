package ru.anydevprojects.locatask.permissionChecker.di

import org.koin.dsl.module
import ru.anydevprojects.locatask.permissionChecker.domain.useCases.IsAllowedPermissionUseCase

val permissionCheckerModule = module {
    factory { IsAllowedPermissionUseCase(get()) }
}
