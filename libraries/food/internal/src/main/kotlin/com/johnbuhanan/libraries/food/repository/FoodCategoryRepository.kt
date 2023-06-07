package com.johnbuhanan.libraries.food.repository

import com.johnbuhanan.common.di.AppScope
import com.johnbuhanan.common.di.SingleIn
import com.johnbuhanan.libraries.food.FoodService
import com.johnbuhanan.libraries.food.model.FoodCategory
import javax.inject.Inject


interface FoodCategoryRepository {
    suspend fun getFoodCategories(): Result<List<FoodCategory>>
}

@SingleIn(AppScope::class)
class FoodCategoryRepositoryImpl @Inject constructor(
    private val foodService: FoodService,
) : FoodCategoryRepository {
    lateinit var cachedCategories: List<FoodCategory>

    override suspend fun getFoodCategories(): Result<List<FoodCategory>> {
        if (this::cachedCategories.isInitialized) {
            return Result.success(cachedCategories)
        }

        return foodService.getFoodCategories().runCatching {
            cachedCategories = foodCategories
            cachedCategories
        }
    }
}
