package com.codingtroops.foodcategorydetails

import com.codingtroops.common.UiEffect
import com.codingtroops.common.UiEvent
import com.codingtroops.common.UiState
import com.codingtroops.networking.FoodItem

sealed class FoodCategoryDetailsEvent : UiEvent {
    data class TappedFoodItem(val message: String) : FoodCategoryDetailsEvent()
    object TappedBack : FoodCategoryDetailsEvent()
}

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val categoryFoodItems: List<FoodItem>,
) : UiState

sealed class FoodCategoryDetailsEffect : UiEffect
