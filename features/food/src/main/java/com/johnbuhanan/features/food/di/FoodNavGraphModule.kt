package com.johnbuhanan.features.food.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FoodNavGraphModule {
//    @Provides
//    @IntoMap
//    @NavGraphKey(FeatureRoute.Food::class)
//    fun provideFoodFeatureNavGraph(): NavGraphSpec {
//        return NavGraphs.food
//    }
}
