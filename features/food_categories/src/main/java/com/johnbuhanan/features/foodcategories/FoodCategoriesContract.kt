package com.johnbuhanan.features.foodcategories

import com.johnbuhanan.common.UiEffect
import com.johnbuhanan.common.UiEvent
import com.johnbuhanan.common.UiState
import com.johnbuhanan.features.networking.FoodItem

sealed class FoodCategoriesEvent : UiEvent {
    data class CategorySelection(val categoryName: String) : FoodCategoriesEvent()
    object TappedBack : FoodCategoriesEvent()
}

data class FoodCategoriesState(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) : UiState

sealed class FoodCategoriesEffect : UiEffect {
    object DataWasLoaded : FoodCategoriesEffect()

    sealed class Navigation : FoodCategoriesEffect() {
        data class ToCategoryDetails(val categoryName: String) : Navigation()
    }
}
