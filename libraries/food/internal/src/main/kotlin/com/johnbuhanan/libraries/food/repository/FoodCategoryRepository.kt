package com.johnbuhanan.libraries.food.repository

import com.johnbuhanan.libraries.food.FoodService
import com.johnbuhanan.libraries.food.model.FoodCategory
import javax.inject.Inject
import javax.inject.Singleton


interface FoodCategoryRepository {
    suspend fun getFoodCategories(): Result<List<FoodCategory>>
}

@Singleton
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
