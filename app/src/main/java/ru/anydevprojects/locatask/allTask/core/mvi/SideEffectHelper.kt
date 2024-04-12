package ru.anydevprojects.locatask.allTask.core.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <Event> CollectEvent(
    event: Flow<Event>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minIntentState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = Dispatchers.Main.immediate,
    onEvent: suspend CoroutineScope.(effect: Event) -> Unit
) {
    LaunchedEffect(event, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minIntentState) {
            if (context == EmptyCoroutineContext) {
                event.collect { onEvent(it) }
            } else {
                withContext(context) {
                    event.collect { onEvent(it) }
                }
            }
        }
    }
}
