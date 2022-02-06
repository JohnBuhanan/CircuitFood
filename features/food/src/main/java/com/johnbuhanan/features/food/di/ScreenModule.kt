package com.johnbuhanan.features.food.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.food.categories.FoodCategoriesScreen
import com.johnbuhanan.navigation.SharedScreen

val featurePostsScreenModule = screenModule {
    register<SharedScreen.FoodCategories> {
        FoodCategoriesScreen()
    }
//    register<SharedScreen.PostDetails> { provider ->
//        DetailsScreen(id = provider.id)
//    }
}