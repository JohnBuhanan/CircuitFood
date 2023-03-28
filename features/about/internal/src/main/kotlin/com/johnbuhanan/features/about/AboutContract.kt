package com.johnbuhanan.features.about

import com.slack.circuit.CircuitUiState

data class AboutState(
    val aboutText: String,
    val isLoading: Boolean,
) : CircuitUiState
