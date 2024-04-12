package ru.anydevprojects.locatask.allTask.presentation.mappers

import ru.anydevprojects.locatask.allTask.presentation.models.PreviewTask
import ru.anydevprojects.locatask.domain.models.Task

fun Task.toPreviewTask(): PreviewTask {
    return PreviewTask(
        id = this.id,
        title = this.title
    )
}