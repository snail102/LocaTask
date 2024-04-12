package ru.anydevprojects.locatask.editTask.data

import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.anydevprojects.locatask.data.mappers.toDomain
import ru.anydevprojects.locatask.database.dao.TaskDao
import ru.anydevprojects.locatask.database.models.TaskEntity
import ru.anydevprojects.locatask.domain.models.Task
import ru.anydevprojects.locatask.editTask.domain.EditTaskRepository

class EditTaskRepositoryImpl(
    private val taskDao: TaskDao
) : EditTaskRepository {
    override suspend fun saveTask(title: String, description: String) {
        val id =
            withContext(Dispatchers.IO) {
                UUID.randomUUID().toString()
            }
        taskDao.insert(
            TaskEntity(
                id = id,
                title = title,
                description = description
            )
        )
    }

    override suspend fun updateTask(id: String, title: String, description: String) {
        taskDao.update(
            TaskEntity(
                id = id,
                title = title,
                description = description
            )
        )
    }

    override suspend fun getTask(taskId: String): Task {
        return taskDao.getTaskById(taskId).toDomain()
    }
}
