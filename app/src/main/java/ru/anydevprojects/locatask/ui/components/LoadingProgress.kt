package ru.anydevprojects.locatask.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProgress(modifier: Modifier = Modifier, text: String = "") {
    Column(modifier = modifier.padding(16.dp)) {
        CircularProgressIndicator()
        if (text.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = text
            )
        }
    }
}
