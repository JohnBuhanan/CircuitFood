package com.codingtroops.foodies.ui.feature.categories

import com.codingtroops.foodies.base.UiEffect
import com.codingtroops.foodies.base.UiEvent
import com.codingtroops.foodies.base.UiState
import com.codingtroops.foodies.model.FoodItem

sealed class FoodCategoriesEvent : UiEvent {
    data class CategorySelection(val categoryName: String) : FoodCategoriesEvent()
}

data class FoodCategoriesState(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) : UiState

sealed class FoodCategoriesEffect : UiEffect {
    object DataWasLoaded : FoodCategoriesEffect()

    sealed class Navigation : FoodCategoriesEffect() {
        data class ToCategoryDetails(val categoryName: String) : Navigation()
    }
}
