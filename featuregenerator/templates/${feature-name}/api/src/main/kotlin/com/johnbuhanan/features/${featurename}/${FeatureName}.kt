package com.johnbuhanan.features.food

import cafe.adriel.voyager.core.registry.ScreenProvider

interface `${FeatureName}` {
    object FoodScreenInput
    object FoodScreenOutput
    sealed class Route : ScreenProvider {
        object FoodCategories : Route()
        data class FoodCategoryDetails(val id: String) : Route()
    }
}
