package com.johnbuhanan.features.food.details

import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState
import com.johnbuhanan.libraries.food.model.FoodItem

sealed class FoodCategoryDetailsEvent : UiEvent {
    data class TappedFoodItem(val message: String) : FoodCategoryDetailsEvent()
    object TappedBack : FoodCategoryDetailsEvent()
}

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val foodItems: List<FoodItem>,
) : UiState

sealed class FoodCategoryDetailsEffect : UiEffect {
    data class ShowToast(val message: String) : FoodCategoryDetailsEffect()
}