package ru.anydevprojects.locatask.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.locatask.settings.presentation.SettingsViewModel

val settingsModule = module {
    viewModel { SettingsViewModel(get(), get(), get()) }
}
