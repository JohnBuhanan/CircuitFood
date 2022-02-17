package com.johnbuhanan.features.food.domain.usecase

import com.johnbuhanan.features.food.domain.mapper.toFoodItem
import com.johnbuhanan.features.food.domain.model.FoodItem
import com.johnbuhanan.features.food.domain.repository.FoodCategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFoodCategoryAsItemById @Inject constructor(private val repository: FoodCategoryRepository) {
    suspend operator fun invoke(categoryId: String): Result<FoodItem> {
        return repository.getFoodCategories().mapCatching { foodCategories ->
            foodCategories.first { it.id == categoryId }.toFoodItem()
        }
    }
}
