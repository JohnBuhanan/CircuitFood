package com.johnbuhanan.common.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

const val LAUNCH_LISTEN_FOR_EFFECTS = "launch-listen-to-effects"

interface UiState

interface UiEvent

interface UiEffect

abstract class BaseViewModel<EVENT : UiEvent, STATE : UiState, EFFECT : UiEffect>(
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {
    private val initialState: STATE by lazy { setInitialState() }
    abstract fun setInitialState(): STATE

    private val _state: MutableState<STATE> = mutableStateOf(initialState)
    val state: State<STATE> = _state

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()

    private val _effect: Channel<EFFECT> = Channel(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    @Suppress("UNCHECKED_CAST")
    fun setEvent(event: UiEvent) {
        viewModelScope.launch(ioDispatcher) { _event.emit(event as EVENT) }
    }

    protected fun setState(reducer: STATE.() -> STATE) {
        viewModelScope.launch(mainDispatcher) {
            val newState = state.value.reducer()
            _state.value = newState
        }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch(ioDispatcher) {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    abstract fun handleEvents(event: EVENT)

    protected fun setEffect(builder: () -> EFFECT) {
        viewModelScope.launch(ioDispatcher) {
            val effectValue = builder()
            _effect.send(effectValue)
        }
    }
}
