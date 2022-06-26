package com.johnbuhanan.features.featureA.screen2

import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState

sealed class Screen2Event : UiEvent {
    object TappedBack : Screen2Event()
    object TappedNext : Screen2Event()
}

data class Screen2State(
    val message: String,
) : UiState

sealed class Screen2Effect : UiEffect {
    data class ShowToast(val message: String) : Screen2Effect()
}
