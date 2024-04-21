package ru.anydevprojects.locatask.allTasks.presentation.mappers

import ru.anydevprojects.locatask.allTasks.presentation.models.PreviewTask
import ru.anydevprojects.locatask.domain.models.Task

fun Task.toPreviewTask(): PreviewTask {
    return PreviewTask(
        id = this.id,
        title = this.title
    )
}
