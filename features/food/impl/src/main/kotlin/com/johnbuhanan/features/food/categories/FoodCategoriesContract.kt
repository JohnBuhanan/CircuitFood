package com.johnbuhanan.features.food.categories

import com.johnbuhanan.common.viewmodel.UiEffect
import com.johnbuhanan.common.viewmodel.UiEvent
import com.johnbuhanan.common.viewmodel.UiState
import com.johnbuhanan.features.food.domain.model.FoodItem

sealed class FoodCategoriesEvent : UiEvent {
    data class TappedCategory(val categoryName: String) : FoodCategoriesEvent()
    object TappedBack : FoodCategoriesEvent()
}

data class FoodCategoriesState(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) : UiState

sealed class FoodCategoriesEffect : UiEffect {
    data class ShowToast(val message: String) : FoodCategoriesEffect()
}
