package com.johnbuhanan.features.food.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.food.categories.FoodCategoriesScreen
import com.johnbuhanan.features.food.details.FoodCategoryDetailsScreen
import com.johnbuhanan.navigation.ScreenModule
import com.johnbuhanan.navigation.SharedScreen.FoodCategories
import com.johnbuhanan.navigation.SharedScreen.FoodCategoryDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object FoodScreenModule {
    @Provides
    @IntoSet
    fun provideScreenModule(): ScreenModule {
        return screenModule {
            register<FoodCategories> {
                FoodCategoriesScreen()
            }
            register<FoodCategoryDetails> {
                FoodCategoryDetailsScreen(it.id)
            }
        }
    }
}
