package ru.anydevprojects.locatask.database.di

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module
import ru.anydevprojects.locatask.database.AppDatabase
import ru.anydevprojects.locatask.database.dao.TaskDao

fun provideDataBase(application: Application): AppDatabase = Room.databaseBuilder(
    application,
    AppDatabase::class.java,
    "AppDatabase"
).fallbackToDestructiveMigration().build()

fun provideTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()


val databaseModule = module {
    single { provideDataBase(get()) }
    single { provideTaskDao(get()) }
}
