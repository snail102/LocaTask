package ru.anydevprojects.locatask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.anydevprojects.locatask.di.appModule
import ru.anydevprojects.locatask.notificationControl.NotificationControl

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        NotificationControl.init(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}
