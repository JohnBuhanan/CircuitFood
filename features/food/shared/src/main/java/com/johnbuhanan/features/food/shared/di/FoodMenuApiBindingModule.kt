package com.johnbuhanan.features.food.shared.di

import com.johnbuhanan.features.food.shared.FoodMenuRepository
import com.johnbuhanan.features.food.shared.FoodMenuRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class FoodMenuApiBindingModule {
    @Binds
    @Singleton
    abstract fun binds(impl: FoodMenuRepositoryImpl): FoodMenuRepository
}
