package com.johnbuhanan.features.food.di

import com.johnbuhanan.common.DirectionTransform
import com.johnbuhanan.common.NavGraphKey
import com.johnbuhanan.common.Route
import com.johnbuhanan.features.food.NavGraphs
import com.johnbuhanan.features.food.destinations.FoodCategoryDetailsScreenDestination
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
object FoodNavGraphModule {
    @Provides
    @IntoMap
    @NavGraphKey(Route.FoodFeature::class)
    fun provideFoodNavGraph(): NavGraphSpec {
        return NavGraphs.food
    }

    @Provides
    @IntoMap
    @NavGraphKey(Route.FoodFeature.FoodCategoryDetails::class)
    fun provideFoodCategoryDetailsScreen(): DirectionTransform {
        return {
            val id = (it as Route.FoodFeature.FoodCategoryDetails).id
            FoodCategoryDetailsScreenDestination(id)
        }
    }
}
