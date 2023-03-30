package com.johnbuhanan.features.food.details

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.johnbuhanan.libraries.food.model.FoodItem
import com.slack.circuit.CircuitUiEvent
import com.slack.circuit.CircuitUiState
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class DetailsResult(val value: String) : Parcelable

sealed interface FoodCategoryDetailsEvent : CircuitUiEvent {
    data class TappedFoodItem(val message: String) : FoodCategoryDetailsEvent
    object TappedBack : FoodCategoryDetailsEvent
}

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val foodItems: List<FoodItem>,
    val eventSink: (FoodCategoryDetailsEvent) -> Unit,
) : CircuitUiState
