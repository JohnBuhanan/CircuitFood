package com.johnbuhanan.features.food.details

import com.johnbuhanan.libraries.food.model.FoodItem

sealed class FoodCategoryDetailsEvent {
    data class TappedFoodItem(val message: String) : FoodCategoryDetailsEvent()
    object TappedBack : FoodCategoryDetailsEvent()
}

data class FoodCategoryDetailsState(
    val category: FoodItem?,
    val foodItems: List<FoodItem>,
)

sealed class FoodCategoryDetailsEffect {
    data class ShowToast(val message: String) : FoodCategoryDetailsEffect()
}