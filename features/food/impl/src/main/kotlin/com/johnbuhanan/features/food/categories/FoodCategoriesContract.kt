package com.johnbuhanan.features.food.categories

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState
import com.johnbuhanan.features.food.domain.FoodItem

sealed class FoodCategoriesEvent : UiEvent {
    data class CategorySelection(val categoryName: String) : FoodCategoriesEvent()
    object TappedBack : FoodCategoriesEvent()
}

data class FoodCategoriesState(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) : UiState

sealed class FoodCategoriesEffect : UiEffect {
    data class ShowSnackbar(val message: String) : FoodCategoriesEffect()
}
