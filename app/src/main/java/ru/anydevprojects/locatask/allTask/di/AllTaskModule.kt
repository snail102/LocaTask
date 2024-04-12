package ru.anydevprojects.locatask.allTask.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.locatask.allTask.data.AllTaskRepositoryImpl
import ru.anydevprojects.locatask.allTask.domain.AllTaskRepository
import ru.anydevprojects.locatask.allTask.presentation.AllTaskViewModel

val allTaskModule = module {
    factory<AllTaskRepository> { AllTaskRepositoryImpl(get()) }
    viewModel { AllTaskViewModel(get()) }
}