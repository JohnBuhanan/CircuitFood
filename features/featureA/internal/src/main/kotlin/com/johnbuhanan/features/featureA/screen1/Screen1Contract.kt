package com.johnbuhanan.features.featureA.screen1

import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState

sealed class Screen1Event : UiEvent {
    object TappedNext : Screen1Event()
}

data class Screen1State(
    val message: String,
) : UiState

sealed class Screen1Effect : UiEffect {
    data class ShowToast(val message: String) : Screen1Effect()
}
