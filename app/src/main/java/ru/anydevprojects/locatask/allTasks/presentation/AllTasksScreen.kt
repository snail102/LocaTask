package ru.anydevprojects.locatask.allTasks.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import ru.anydevprojects.locatask.allTasks.presentation.models.PreviewTask
import ru.anydevprojects.locatask.root.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllTasksScreen(
    navController: NavHostController,
    viewModel: AllTasksViewModel = koinViewModel()
) {
    val applicationContext = LocalContext.current.applicationContext

    val state by viewModel.state.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.EditTask.getRouteWithArgs()) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = state.allTask, key = { item: PreviewTask ->
                item.id
            }) { task ->
                TaskItem(
                    modifier = Modifier.fillMaxWidth(),
                    name = task.title,
                    description = ""
                )
            }
        }
    }
}

@Composable
private fun TaskItem(modifier: Modifier, name: String, description: String) {
    Column(
        modifier = modifier
            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .background(color = Color.Cyan, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name
        )
        if (description.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = description
            )
        }
    }
}
