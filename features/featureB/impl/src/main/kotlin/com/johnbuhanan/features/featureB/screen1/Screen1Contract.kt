package com.johnbuhanan.features.featureB.screen1

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState

sealed class Screen1Event : UiEvent {
    object TappedNext : Screen1Event()
}

data class Screen1State(
    val message: String,
) : UiState

sealed class Screen1Effect : UiEffect {
    data class ShowToast(val message: String) : Screen1Effect()
}
