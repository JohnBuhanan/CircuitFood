package com.codingtroops.featureA

import com.codingtroops.common.UiEffect
import com.codingtroops.common.UiEvent
import com.codingtroops.common.UiState

sealed class FeatureAEvent : UiEvent

data class FeatureAState(
    val thing: String,
) : UiState

sealed class FeatureAEffect : UiEffect
