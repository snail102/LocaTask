package ru.anydevprojects.locatask.allTask.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MviDelegate<UiState, UiAction, SideEffect> internal constructor(
    initialUiState: UiState
) : MVI<UiState, UiAction, SideEffect> {

    private val _state = MutableStateFlow(initialUiState)
    override val state: StateFlow<UiState> = _state.asStateFlow()

    private val _event by lazy { Channel<SideEffect>() }
    override val event: Flow<SideEffect> by lazy { _event.receiveAsFlow() }
    override val lastState: UiState
        get() = _state.value

    override fun onIntent(intent: UiAction) {}

    override fun updateState(newUiState: UiState) {
        _state.update { newUiState }
    }

    override fun updateState(block: UiState.() -> UiState) {
        _state.update(block)
    }

    override fun CoroutineScope.emitEvent(event: SideEffect) {
        this.launch { _event.send(event) }
    }
}

fun <UiState, UiAction, SideEffect> mvi(
    initialUiState: UiState
): MVI<UiState, UiAction, SideEffect> = MviDelegate(initialUiState)
