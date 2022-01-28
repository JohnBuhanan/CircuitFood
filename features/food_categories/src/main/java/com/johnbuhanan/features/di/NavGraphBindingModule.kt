package com.johnbuhanan.features.di

import com.johnbuhanan.common.DirectionTransform
import com.johnbuhanan.common.NavGraphKey
import com.johnbuhanan.common.Route
import com.johnbuhanan.features.NavGraph
import com.johnbuhanan.features.destinations.FoodCategoriesDestination
import com.johnbuhanan.features.destinations.FoodCategoryDetailsDestination
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

/**
 * This module builds a Map of all the Workers that we support in our app. This is used in the M1WorkerFactory to actually
 * create each Worker for use with WorkManager and Dagger.
 */
@Module
@InstallIn(SingletonComponent::class)
object NavGraphModule {
    @Provides
    @IntoMap
    @NavGraphKey(Route.FoodCategories::class)
    fun provideNavGraphSpec(): NavGraphSpec {
        return NavGraph(
            route = "root",
            startDestination = FoodCategoriesDestination,
            destinations = listOf(
                FoodCategoriesDestination,
                FoodCategoryDetailsDestination
            )
        )
    }

    @Provides
    @IntoMap
    @NavGraphKey(Route.FoodCategoryDetails::class)
    fun provideDirections(): DirectionTransform {
        return {
            val id = (it as Route.FoodCategoryDetails).id
            FoodCategoryDetailsDestination(id)
        }
    }
}
