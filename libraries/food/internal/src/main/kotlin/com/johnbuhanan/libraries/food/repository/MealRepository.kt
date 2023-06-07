package com.johnbuhanan.libraries.food.repository

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.johnbuhanan.libraries.food.FoodService
import com.johnbuhanan.libraries.food.model.Meal
import javax.inject.Inject

interface MealRepository {
    suspend fun getMealsByCategoryName(categoryName: String): Result<List<Meal>>
}

@SingleIn(AppScope::class)
class MealRepositoryImpl @Inject constructor(
    private val foodService: FoodService
) : MealRepository {
    override suspend fun getMealsByCategoryName(categoryName: String): Result<List<Meal>> {
        return foodService.getMealsByCategoryName(categoryName).runCatching { meals }
    }
}
