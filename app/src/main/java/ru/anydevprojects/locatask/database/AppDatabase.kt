package ru.anydevprojects.locatask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.anydevprojects.locatask.database.dao.TaskDao
import ru.anydevprojects.locatask.database.models.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
