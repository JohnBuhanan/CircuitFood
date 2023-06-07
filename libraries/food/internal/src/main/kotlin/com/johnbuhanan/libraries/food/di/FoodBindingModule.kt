package com.johnbuhanan.libraries.food.di

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
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
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module

@ContributesTo(AppScope::class)
@Module
abstract class FoodBindingModule {
    @Binds
    @SingleIn(AppScope::class)
    abstract fun bindsFoodCategoryRepository(impl: FoodCategoryRepositoryImpl): FoodCategoryRepository

    @Binds
    @SingleIn(AppScope::class)
    abstract fun bindsMealRepository(impl: MealRepositoryImpl): MealRepository

    @Binds
    @SingleIn(AppScope::class)
    abstract fun bindsGetFoodCategoriesAsItems(impl: GetFoodCategoriesAsItemsImpl): GetFoodCategoriesAsItems

    @Binds
    @SingleIn(AppScope::class)
    abstract fun bindsGetFoodCategoryAsItemById(impl: GetFoodCategoryAsItemByIdImpl): GetFoodCategoryAsItemById

    @Binds
    @SingleIn(AppScope::class)
    abstract fun bindsGetMealsAsItemsById(impl: GetMealsAsItemsByIdImpl): GetMealsAsItemsById
}
