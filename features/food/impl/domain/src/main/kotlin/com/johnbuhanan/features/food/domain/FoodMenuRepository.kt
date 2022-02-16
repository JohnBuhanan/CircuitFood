package com.johnbuhanan.features.food.domain

import com.johnbuhanan.features.food.domain.model.FoodCategoriesResponse
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.domain.model.MealsResponse
import javax.inject.Inject
import javax.inject.Singleton

interface FoodMenuRepository {
    suspend fun getFoodCategories(): Result<List<FoodItem>>
    suspend fun getMealsByCategory(categoryId: String): Result<List<FoodItem>>
}

@Singleton
class FoodMenuRepositoryImpl @Inject constructor(private val foodService: FoodService) : FoodMenuRepository {
    private var cachedCategories: List<FoodItem>? = null

    override suspend fun getFoodCategories(): Result<List<FoodItem>> {
        cachedCategories?.let {
            return Result.success(it)
        }

        return foodService.getFoodCategories().runCatching {
            val categories = this.toFoodItems()
            cachedCategories = categories
            categories
        }
    }

    override suspend fun getMealsByCategory(categoryId: String): Result<List<FoodItem>> {
        return getFoodCategories().mapCatching {
            foodService.getMealsByCategory(categoryId).toFoodItems()
        }
    }

    private fun FoodCategoriesResponse.toFoodItems(): List<FoodItem> {
        return this.categories.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    private fun MealsResponse.toFoodItems(): List<FoodItem> {
        return this.meals.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }
}
