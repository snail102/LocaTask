package ru.anydevprojects.locatask.core.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVI<State, Intent, Event> {
    val state: StateFlow<State>
    val event: Flow<Event>

    val lastState: State

    fun onIntent(intent: Intent)

    fun updateState(block: State.() -> State)

    fun updateState(newUiState: State)

    fun CoroutineScope.emitEvent(event: Event)
}

@Stable
@Composable
fun <State, Intent, Event> MVI<State, Intent, Event>.unpack() =
    Triple(state.collectAsState().value, ::onIntent, event)
