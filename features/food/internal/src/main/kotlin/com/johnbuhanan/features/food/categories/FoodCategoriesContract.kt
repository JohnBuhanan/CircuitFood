package com.johnbuhanan.features.food.categories

import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.CircuitUiEvent
import com.slack.circuit.CircuitUiState

sealed interface FoodCategoriesEvent : CircuitUiEvent {
    data class TappedCategory(val id: String) : FoodCategoriesEvent
}

data class FoodCategoriesState(
    val categories: List<FoodItem> = listOf(),
    val isLoading: Boolean = false,
    val resultText: String = "",
    val eventSink: (FoodCategoriesEvent) -> Unit,
) : CircuitUiState

sealed class FoodCategoriesEffect {
    data class ShowToast(val message: String) : FoodCategoriesEffect()
}

