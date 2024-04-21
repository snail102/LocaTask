package ru.anydevprojects.locatask.allTasks.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anydevprojects.locatask.allTasks.domain.AllTasksRepository
import ru.anydevprojects.locatask.data.mappers.toDomain
import ru.anydevprojects.locatask.database.dao.TaskDao
import ru.anydevprojects.locatask.domain.models.Task

class AllTasksRepositoryImpl(
    private val taskDao: TaskDao
) : AllTasksRepository {
    override fun allTasks(): Flow<List<Task>> {
        return taskDao.getAllTaskFlow().map { allTask ->
            allTask.map { taskEntity ->
                taskEntity.toDomain()
            }
        }
    }
}
