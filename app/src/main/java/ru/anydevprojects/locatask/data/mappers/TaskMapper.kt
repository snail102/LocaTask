package ru.anydevprojects.locatask.data.mappers

import ru.anydevprojects.locatask.database.models.TaskEntity
import ru.anydevprojects.locatask.domain.models.Task


fun TaskEntity.toDomain(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description
    )
}