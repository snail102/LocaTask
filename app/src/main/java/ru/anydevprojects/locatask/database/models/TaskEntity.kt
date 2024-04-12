package ru.anydevprojects.locatask.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String

)