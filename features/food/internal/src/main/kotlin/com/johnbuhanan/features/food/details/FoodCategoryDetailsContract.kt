package com.johnbuhanan.features.food.details

import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.CircuitUiEvent
import com.slack.circuit.CircuitUiState

sealed interface FoodCategoryDetailsEvent : CircuitUiEvent {
    data class TappedFoodItem(val message: String) : FoodCategoryDetailsEvent
    object TappedBack : FoodCategoryDetailsEvent
}

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val foodItems: List<FoodItem>,
    val eventSink: (FoodCategoryDetailsEvent) -> Unit,
) : CircuitUiState
