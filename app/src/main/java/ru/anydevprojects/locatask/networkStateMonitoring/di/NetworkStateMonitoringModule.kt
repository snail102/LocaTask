package ru.anydevprojects.locatask.networkStateMonitoring.di

import org.koin.dsl.module
import ru.anydevprojects.locatask.networkStateMonitoring.domain.useCases.IsAliveNetworkStateMonitoringUseCase

val networkStateMonitoringModule = module {
    factory { IsAliveNetworkStateMonitoringUseCase(get()) }
}
