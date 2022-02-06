package com.johnbuhanan.features.featureA

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState

sealed class FeatureAEvent : UiEvent

data class FeatureAState(
    val thing: String,
) : UiState

sealed class FeatureAEffect : UiEffect
