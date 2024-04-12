package ru.anydevprojects.locatask.editTask.domain

import ru.anydevprojects.locatask.domain.models.Task

interface EditTaskRepository {
    suspend fun saveTask(title: String, description: String)

    suspend fun updateTask(id: String, title: String, description: String)

    suspend fun getTask(taskId: String): Task
}
