package com.johnbuhanan.features.featureA.screen2

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState
import com.johnbuhanan.features.featureA.screen1.Screen1Effect
import com.johnbuhanan.features.featureA.screen1.Screen1Event

sealed class Screen2Event : UiEvent {
    object TappedNext : Screen2Event()
}

data class Screen2State(
    val message: String,
) : UiState

sealed class Screen2Effect : UiEffect {
    data class ShowToast(val message: String) : Screen2Effect()
}
