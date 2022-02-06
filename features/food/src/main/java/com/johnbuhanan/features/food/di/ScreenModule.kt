package com.johnbuhanan.features.food.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.food.categories.FoodCategoriesScreen
import com.johnbuhanan.features.food.details.FoodCategoryDetailsScreen
import com.johnbuhanan.navigation.SharedScreen

val featureScreenModule = screenModule {
    register<SharedScreen.FoodCategories> {
        FoodCategoriesScreen()
    }
    register<SharedScreen.FoodCategoryDetails> {
        FoodCategoryDetailsScreen(it.id)
    }
}