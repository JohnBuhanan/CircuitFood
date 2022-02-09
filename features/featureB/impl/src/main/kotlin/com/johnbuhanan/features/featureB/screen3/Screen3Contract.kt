package com.johnbuhanan.features.featureB.screen3

import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState

sealed class Screen3Event : UiEvent {
    object TappedNext : Screen3Event()
}

data class Screen3State(
    val message: String,
) : UiState

sealed class Screen3Effect : UiEffect {
    data class ShowToast(val message: String) : Screen3Effect()
}
