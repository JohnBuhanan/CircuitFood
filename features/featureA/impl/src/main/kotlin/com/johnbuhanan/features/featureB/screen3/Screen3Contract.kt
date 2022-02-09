package com.johnbuhanan.features.featureB.screen3

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState

sealed class Screen3Event : UiEvent {
    object TappedNext : Screen3Event()
}

data class Screen3State(
    val message: String,
) : UiState

sealed class Screen3Effect : UiEffect {
    data class ShowToast(val message: String) : Screen3Effect()
}
