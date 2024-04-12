package ru.anydevprojects.locatask.allTask.domain

import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.locatask.domain.models.Task

interface AllTaskRepository {

    fun allTask(): Flow<List<Task>>
}