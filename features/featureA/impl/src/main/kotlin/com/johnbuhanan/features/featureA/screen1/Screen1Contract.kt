package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState

sealed class Screen1Event : UiEvent

data class Screen1State(
    val thing: String,
) : UiState

sealed class Screen1Effect : UiEffect {
    data class ShowToast(val message: String) : Screen1Effect()
}
