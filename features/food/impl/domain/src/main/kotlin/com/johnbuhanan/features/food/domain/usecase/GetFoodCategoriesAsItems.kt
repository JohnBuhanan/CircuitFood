package com.johnbuhanan.features.food.domain.usecase

import com.johnbuhanan.features.food.domain.mapper.toFoodItem
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFoodCategoriesAsItems @Inject constructor(private val repository: FoodCategoryRepository) {
    suspend operator fun invoke(): Result<List<FoodItem>> {
        return repository.getFoodCategories().mapCatching { foodCategories ->
            foodCategories.map { foodCategory ->
                foodCategory.toFoodItem()
            }
        }
    }
}