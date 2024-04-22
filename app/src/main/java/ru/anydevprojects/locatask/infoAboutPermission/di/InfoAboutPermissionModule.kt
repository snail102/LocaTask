package ru.anydevprojects.locatask.infoAboutPermission.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.locatask.infoAboutPermission.presentation.InfoAboutPermissionViewModel

val infoAboutMonitoringModule = module {
    viewModel { InfoAboutPermissionViewModel(get(), get()) }
}
