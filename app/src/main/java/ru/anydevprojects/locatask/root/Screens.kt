package ru.anydevprojects.locatask.root

sealed interface Screens {
    val route: String

    data object AllTask : Screens {
        override val route: String = "allTask"
    }

    data object EditTask : Screens {
        override val route: String = "editTask?={taskId}"

        val taskIdArg: String = "taskId"

        fun getRouteWithArgs(taskIdArg: String = ""): String {
            return "editTask?=$taskIdArg"
        }
    }
}