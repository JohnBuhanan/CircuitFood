package com.johnbuhanan.features.about

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

sealed interface AboutEvent : CircuitUiEvent {
    object TappedBack : AboutEvent
}

data class AboutState(
    val aboutText: String,
    val eventSink: (AboutEvent) -> Unit
) : CircuitUiState
