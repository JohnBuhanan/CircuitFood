package com.johnbuhanan.features.food.domain.di

import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepository
import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepositoryImpl
import com.johnbuhanan.features.food.domain.repository.MealRepository
import com.johnbuhanan.features.food.domain.repository.MealRepositoryImpl
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
    abstract fun bindsFoodCategoryRepository(impl: FoodCategoryRepositoryImpl): FoodCategoryRepository

    @Binds
    @Singleton
    abstract fun bindsMealRepository(impl: MealRepositoryImpl): MealRepository
}
