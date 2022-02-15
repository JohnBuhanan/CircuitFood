package com.johnbuhanan.features.${featurename}.di

import cafe.adriel.voyager.core.registry.screenModule
import com.johnbuhanan.features.food.Food
import com.johnbuhanan.features.food.categories.FoodCategoriesScreen
import com.johnbuhanan.features.food.details.FoodCategoryDetailsScreen
import com.johnbuhanan.navigation.ScreenModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object `${FeatureName}ScreenModule` {
    @Provides
    @IntoSet
    fun provideScreenModule(): ScreenModule {
        return screenModule {
            register<Food.Route.FoodCategories> {
                FoodCategoriesScreen()
            }
            register<Food.Route.FoodCategoryDetails> {
                FoodCategoryDetailsScreen(it.id)
            }
        }
    }
}
