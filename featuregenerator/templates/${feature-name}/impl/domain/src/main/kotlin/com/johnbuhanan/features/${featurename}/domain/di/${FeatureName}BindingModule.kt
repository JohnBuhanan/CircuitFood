package com.johnbuhanan.features.food.domain.di

import com.johnbuhanan.features.food.domain.FoodMenuRepository
import com.johnbuhanan.features.food.domain.FoodMenuRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class `${FeatureName}BindingModule` {
    @Binds
    @Singleton
    abstract fun binds(impl: FoodMenuRepositoryImpl): FoodMenuRepository
}
