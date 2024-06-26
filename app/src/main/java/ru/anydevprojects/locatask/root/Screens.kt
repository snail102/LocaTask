package ru.anydevprojects.locatask.root

sealed interface Screens {
    val route: String

    data object BottomNavGraph : Screens {
        override val route: String = "bottomNavGraph"
    }

    data object AllTasks : Screens {
        override val route: String = "allTasks"
    }

    data object Settings : Screens {
        override val route: String = "settings"
    }

    data object InfoAboutPermission : Screens {
        override val route: String = "infoAboutPermission?={permission}"

        val permissionArg: String = "permission"

        fun getRouteWithArgs(permissionArg: String): String {
            return "infoAboutPermission?=$permissionArg"
        }
    }

    data object EditTask : Screens {
        override val route: String = "editTask?={taskId}"

        val taskIdArg: String = "taskId"

        fun getRouteWithArgs(taskIdArg: String = ""): String {
            return "editTask?=$taskIdArg"
        }
    }
}
