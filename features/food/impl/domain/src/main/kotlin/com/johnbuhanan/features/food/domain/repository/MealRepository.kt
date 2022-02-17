package com.johnbuhanan.features.food.domain.repository

import com.johnbuhanan.features.food.domain.FoodService
import com.johnbuhanan.features.food.domain.model.Meal
import javax.inject.Inject
import javax.inject.Singleton

interface MealRepository {
    suspend fun getMealsByCategoryName(categoryName: String): Result<List<Meal>>
}

@Singleton
class MealRepositoryImpl @Inject constructor(private val foodService: FoodService) : MealRepository {
    override suspend fun getMealsByCategoryName(categoryName: String): Result<List<Meal>> {
        return foodService.getMealsByCategoryName(categoryName).runCatching { meals }
    }
}
