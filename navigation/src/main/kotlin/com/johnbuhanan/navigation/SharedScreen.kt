package com.johnbuhanan.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    object FoodCategories : SharedScreen()
    data class FoodCategoryDetails(val id: String) : SharedScreen()
    object FeatureA : SharedScreen()
}
