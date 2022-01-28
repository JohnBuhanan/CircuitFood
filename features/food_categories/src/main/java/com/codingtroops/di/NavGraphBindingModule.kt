package com.codingtroops.di

import com.codingtroops.NavGraph
import com.codingtroops.common.DirectionTransform
import com.codingtroops.common.NavGraphKey
import com.codingtroops.common.Route
import com.codingtroops.destinations.FoodCategoriesDestination
import com.codingtroops.destinations.FoodCategoryDetailsDestination
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
