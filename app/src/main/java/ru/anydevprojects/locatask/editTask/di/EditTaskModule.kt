package ru.anydevprojects.locatask.editTask.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.locatask.editTask.data.EditTaskRepositoryImpl
import ru.anydevprojects.locatask.editTask.domain.EditTaskRepository
import ru.anydevprojects.locatask.editTask.presentation.EditTaskViewModel

val editTaskModule =
    module {
        factory<EditTaskRepository> { EditTaskRepositoryImpl(get()) }
        viewModel { EditTaskViewModel(get(), get()) }
    }
