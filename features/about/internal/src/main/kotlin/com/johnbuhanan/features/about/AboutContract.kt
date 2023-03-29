package com.johnbuhanan.features.about

import com.slack.circuit.CircuitUiEvent
import com.slack.circuit.CircuitUiState

sealed interface AboutEvent : CircuitUiEvent {
    object TappedBack : AboutEvent
}

data class AboutState(
    val aboutText: String,
    val eventSink: (AboutEvent) -> Unit
) : CircuitUiState
