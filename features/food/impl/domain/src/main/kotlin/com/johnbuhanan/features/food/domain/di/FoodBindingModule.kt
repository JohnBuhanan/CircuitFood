package com.johnbuhanan.features.food.domain.di

import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepository
import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepositoryImpl
import com.johnbuhanan.features.food.domain.repository.MealRepository
import com.johnbuhanan.features.food.domain.repository.MealRepositoryImpl
import com.johnbuhanan.features.food.domain.usecase.GetFoodCategoriesAsItems
import com.johnbuhanan.features.food.domain.usecase.GetFoodCategoriesAsItemsImpl
import com.johnbuhanan.features.food.domain.usecase.GetFoodCategoryAsItemById
import com.johnbuhanan.features.food.domain.usecase.GetFoodCategoryAsItemByIdImpl
import com.johnbuhanan.features.food.domain.usecase.GetMealsAsItemsById
import com.johnbuhanan.features.food.domain.usecase.GetMealsAsItemsByIdImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class FoodBindingModule {
    @Binds
    @Singleton
    abstract fun bindsFoodCategoryRepository(impl: FoodCategoryRepositoryImpl): FoodCategoryRepository

    @Binds
    @Singleton
    abstract fun bindsMealRepository(impl: MealRepositoryImpl): MealRepository

    @Binds
    @Singleton
    abstract fun bindsGetFoodCategoriesAsItems(impl: GetFoodCategoriesAsItemsImpl): GetFoodCategoriesAsItems

    @Binds
    @Singleton
    abstract fun bindsGetFoodCategoryAsItemById(impl: GetFoodCategoryAsItemByIdImpl): GetFoodCategoryAsItemById

    @Binds
    @Singleton
    abstract fun bindsGetMealsAsItemsById(impl: GetMealsAsItemsByIdImpl): GetMealsAsItemsById
}
