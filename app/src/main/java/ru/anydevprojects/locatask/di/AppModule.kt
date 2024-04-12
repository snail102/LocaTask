package ru.anydevprojects.locatask.di

import org.koin.dsl.module
import ru.anydevprojects.locatask.allTask.di.allTaskModule
import ru.anydevprojects.locatask.database.di.databaseModule
import ru.anydevprojects.locatask.editTask.di.editTaskModule

val appModule = module {
    includes(
        allTaskModule,
        editTaskModule,
        databaseModule
    )

}