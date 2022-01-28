package com.codingtroops.foodcategories

import com.codingtroops.common.UiEffect
import com.codingtroops.common.UiEvent
import com.codingtroops.common.UiState
import com.codingtroops.networking.FoodItem

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
