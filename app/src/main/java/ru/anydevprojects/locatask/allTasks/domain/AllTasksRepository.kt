package ru.anydevprojects.locatask.allTasks.domain

import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.locatask.domain.models.Task

interface AllTasksRepository {

    fun allTasks(): Flow<List<Task>>
}
