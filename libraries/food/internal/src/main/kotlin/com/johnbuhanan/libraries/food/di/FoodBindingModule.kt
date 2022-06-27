package com.johnbuhanan.libraries.food.di

import com.johnbuhanan.libraries.food.repository.FoodCategoryRepository
import com.johnbuhanan.libraries.food.repository.FoodCategoryRepositoryImpl
import com.johnbuhanan.libraries.food.repository.MealRepository
import com.johnbuhanan.libraries.food.repository.MealRepositoryImpl
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItems
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoriesAsItemsImpl
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoryAsItemById
import com.johnbuhanan.libraries.food.usecase.GetFoodCategoryAsItemByIdImpl
import com.johnbuhanan.libraries.food.usecase.GetMealsAsItemsById
import com.johnbuhanan.libraries.food.usecase.GetMealsAsItemsByIdImpl
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
