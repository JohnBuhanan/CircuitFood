package com.johnbuhanan.features.food.categories

import com.johnbuhanan.libraries.food.model.FoodItem

sealed class FoodCategoriesEvent {
    data class TappedCategory(val id: String) : FoodCategoriesEvent()
    object TappedBack : FoodCategoriesEvent()
}

data class FoodCategoriesState(
    val categories: List<FoodItem> = listOf(),
    val isLoading: Boolean = false
)

sealed class FoodCategoriesEffect {
    data class ShowToast(val message: String) : FoodCategoriesEffect()
}