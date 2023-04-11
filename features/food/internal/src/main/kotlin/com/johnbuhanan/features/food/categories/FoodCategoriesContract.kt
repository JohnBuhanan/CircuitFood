package com.johnbuhanan.features.food.categories

import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

sealed interface FoodCategoriesEvent : CircuitUiEvent {
    data class TappedCategory(val id: String) : FoodCategoriesEvent
    object TappedBack : FoodCategoriesEvent
}

sealed interface FoodCategoriesState : CircuitUiState {
    object Loading : FoodCategoriesState

    data class Success(
        val categories: List<FoodItem> = listOf(),
        val resultText: String = "",
        val eventSink: (FoodCategoriesEvent) -> Unit,
    ) : FoodCategoriesState
}