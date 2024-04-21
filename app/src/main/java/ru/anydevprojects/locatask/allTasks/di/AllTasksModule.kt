package ru.anydevprojects.locatask.allTasks.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.locatask.allTasks.data.AllTasksRepositoryImpl
import ru.anydevprojects.locatask.allTasks.domain.AllTasksRepository
import ru.anydevprojects.locatask.allTasks.presentation.AllTasksViewModel

val allTaskModule = module {
    factory<AllTasksRepository> { AllTasksRepositoryImpl(get()) }
    viewModel { AllTasksViewModel(get()) }
}
