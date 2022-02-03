package com.johnbuhanan.features.food.di

import com.johnbuhanan.common.FeatureRoute
import com.johnbuhanan.common.NavGraphKey
import com.johnbuhanan.features.food.NavGraphs
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
    @NavGraphKey(FeatureRoute.Food::class)
    fun provideFoodFeatureNavGraph(): NavGraphSpec {
        return NavGraphs.food
    }
}
