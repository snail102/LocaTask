package ru.anydevprojects.locatask.allTask.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anydevprojects.locatask.allTask.domain.AllTaskRepository
import ru.anydevprojects.locatask.data.mappers.toDomain
import ru.anydevprojects.locatask.database.dao.TaskDao
import ru.anydevprojects.locatask.domain.models.Task

class AllTaskRepositoryImpl(
    private val taskDao: TaskDao
) : AllTaskRepository {
    override fun allTask(): Flow<List<Task>> {
        return taskDao.getAllTaskFlow().map { allTask ->
            allTask.map { taskEntity ->
                taskEntity.toDomain()
            }
        }
    }
}