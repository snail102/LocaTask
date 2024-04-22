package ru.anydevprojects.locatask.di

import org.koin.dsl.module
import ru.anydevprojects.locatask.allTasks.di.allTaskModule
import ru.anydevprojects.locatask.database.di.databaseModule
import ru.anydevprojects.locatask.editTask.di.editTaskModule
import ru.anydevprojects.locatask.infoAboutPermission.di.infoAboutMonitoringModule
import ru.anydevprojects.locatask.networkStateMonitoring.di.networkStateMonitoringModule
import ru.anydevprojects.locatask.permissionChecker.di.permissionCheckerModule
import ru.anydevprojects.locatask.settings.di.settingsModule

val appModule = module {
    includes(
        allTaskModule,
        editTaskModule,
        databaseModule,
        settingsModule,
        networkStateMonitoringModule,
        infoAboutMonitoringModule,
        permissionCheckerModule
    )
}
